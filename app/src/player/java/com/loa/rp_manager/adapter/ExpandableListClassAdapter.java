package com.loa.rp_manager.adapter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.stmt.QueryBuilder;
import com.loa.rp_manager.R;
import com.loa.rp_manager.db.CatComptDb;
import com.loa.rp_manager.db.JobDb;
import com.loa.rp_manager.db.ComptDb;
import com.loa.rp_manager.db.JobHasCatComptDb;
import com.loa.rp_manager.db.JobHasComptDb;
import com.loa.rp_manager.utils.Utils;

public class ExpandableListClassAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, JobDb> _listDataChild;

    public ExpandableListClassAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, JobDb> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public JobDb getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final JobDb jobDb = getChild(groupPosition, childPosition);
        LayoutInflater infalInflater = (LayoutInflater) this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = infalInflater.inflate(R.layout.list_item_class, null);
        }

        TextView desc = (TextView) convertView.findViewById(R.id.list_item_class_description);
        desc.setText(jobDb.getDescription());

        TextView achetype = (TextView) convertView.findViewById(R.id.list_item_class_achetype);
        achetype.setText(jobDb.getAchetype());

        TextView multi = (TextView) convertView.findViewById(R.id.list_item_class_multi);
        multi.setText(String.valueOf(jobDb.getMultiplicateurPv()));

        TextView pv = (TextView) convertView.findViewById(R.id.list_item_class_pv);
        pv.setText(String.valueOf(jobDb.getPv()));

        TextView init = (TextView) convertView.findViewById(R.id.list_item_class_init);
        init.setText(String.valueOf(jobDb.getInitiative()));

        TextView runeInne = (TextView) convertView.findViewById(R.id.list_item_class_rune_inne);
        runeInne.setText(String.valueOf(jobDb.getRuneInne()));

        generatedPrincipalCompetence(jobDb, (LinearLayout) convertView, infalInflater);

        return convertView;
    }

    private void generatedPrincipalCompetence(JobDb jobDb, LinearLayout convertView, LayoutInflater infalInflater) {
        try {
            Dao<CatComptDb, ?> catComptDbDao = Utils.getHelper().getDao(CatComptDb.class);
            QueryBuilder<CatComptDb, ?> catComptDbQueryBuilder = catComptDbDao.queryBuilder();
            catComptDbQueryBuilder.where().eq(CatComptDb.PRINCIPAL, 1);

            // Cherche les competences par leur priorité
            List<CatComptDb> catComptDbs = catComptDbDao.query(catComptDbQueryBuilder.prepare());
            for (CatComptDb catComptDb : catComptDbs) {
                // Recuperer la valuer par défault de la competence
                Dao<JobHasCatComptDb, ?> jobHasCatComptDbs = Utils.getHelper().getDao(JobHasCatComptDb.class);
                QueryBuilder<JobHasCatComptDb, ?> comptDbQueryBuilder = jobHasCatComptDbs.queryBuilder();
                comptDbQueryBuilder.where().eq(JobHasCatComptDb.CLASS, jobDb.getId())
                        .and().eq(JobHasCatComptDb.CAT_COMPT, catComptDb.getId());
                JobHasCatComptDb jobHasCatComptDb = jobHasCatComptDbs.queryForFirst(comptDbQueryBuilder.prepare());

                // Genere une ligne de competence global
                RelativeLayout competencesView = (RelativeLayout) infalInflater.inflate(R.layout.list_class_competences, null);
                TextView titleCompetences = (TextView) competencesView.findViewById(R.id.list_class_competences_title);
                titleCompetences.setText(catComptDb.getTitre());

                // Parcours la liste des competences
                ForeignCollection<ComptDb> comptDbs = catComptDb.getListCompt();
                for (ComptDb comptDb : comptDbs) {
                    View competenceView = infalInflater.inflate(R.layout.list_class_competence, null);
                    TextView titleCompetence = (TextView) competenceView.findViewById(R.id.list_class_competence_title);
                    titleCompetence.setText(comptDb.getTitre());

                    TextView valueCompetence = (TextView) competenceView.findViewById(R.id.list_class_competence_value);

                    // Hydrate la valeurs des competences
                    Dao<JobHasComptDb, ?> jobHasComptDbs = Utils.getHelper().getDao(JobHasComptDb.class);
                    QueryBuilder<JobHasComptDb, ?> jobHasComptDbQueryBuilder = jobHasComptDbs.queryBuilder();
                    jobHasComptDbQueryBuilder.where().eq(JobHasComptDb.CLASS, jobDb.getId())
                    .and().eq(JobHasComptDb.COMPT, comptDb.getId());

                    JobHasComptDb jobHasComptDb = jobHasComptDbs.queryForFirst(jobHasComptDbQueryBuilder.prepare());
                    // Si la competences n'est pas trouver, on met la valeur par default
                    if(jobHasComptDb.equals(null)){
                        valueCompetence.setText(jobHasCatComptDb.getDefault_cout());
                    } else {
                        valueCompetence.setText(jobHasComptDb.getValue());
                    }

                    competencesView.addView(competenceView);
                }

                convertView.addView(competencesView);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_class, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lg_class_titre);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}