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
import com.loa.rp_manager.db.ClassDb;
import com.loa.rp_manager.db.ComptDb;
import com.loa.rp_manager.db.ClassHasCatComptDb;
import com.loa.rp_manager.db.ClassHasComptDb;
import com.loa.rp_manager.utils.Utils;

public class ExpandableListClassAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ClassDb> _listDataChild;

    private int id;

    public ExpandableListClassAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, ClassDb> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
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
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public ClassDb getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ClassDb classDb = getChild(groupPosition, childPosition);
        LayoutInflater infalInflater = (LayoutInflater) this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.list_item_class, null);

        TextView desc = (TextView) convertView.findViewById(R.id.list_item_class_description);
        desc.setText(classDb.getDescription());

        TextView achetype = (TextView) convertView.findViewById(R.id.list_item_class_achetype);
        achetype.setText(classDb.getAchetype());

        TextView multi = (TextView) convertView.findViewById(R.id.list_item_class_multi);
        multi.setText(String.valueOf(classDb.getMultiplicateurPv()));

        TextView pv = (TextView) convertView.findViewById(R.id.list_item_class_pv);
        pv.setText(String.valueOf(classDb.getPv()));

        TextView init = (TextView) convertView.findViewById(R.id.list_item_class_init);
        init.setText(String.valueOf(classDb.getInitiative()));

        TextView runeInne = (TextView) convertView.findViewById(R.id.list_item_class_rune_inne);
        runeInne.setText(String.valueOf(classDb.getRuneInne()));

        id = 1;
        generatedCompetence(classDb, (LinearLayout) convertView, infalInflater, 1);
        generatedCompetence(classDb, (LinearLayout) convertView, infalInflater, 0);

        return convertView;
    }

    /**
     *
     * @param classDb
     * @param convertView
     * @param infalInflater
     * @param principal
     */
    private void generatedCompetence(ClassDb classDb, LinearLayout convertView, LayoutInflater infalInflater, int principal) {
        try {
            List<CatComptDb> catComptDbs = getCatComptDbs(principal);
            for (CatComptDb catComptDb : catComptDbs) {
                // Recuperer la valuer par défault de la competence
                Dao<ClassHasCatComptDb, ?> jobHasCatComptDbs = Utils.getHelper().getDao(ClassHasCatComptDb.class);
                QueryBuilder<ClassHasCatComptDb, ?> comptDbQueryBuilder = jobHasCatComptDbs.queryBuilder();
                comptDbQueryBuilder.where().eq(ClassHasCatComptDb.JOB, classDb.getId())
                        .and().eq(ClassHasCatComptDb.CAT_COMPT, catComptDb.getId());
                ClassHasCatComptDb classHasCatComptDb = jobHasCatComptDbs.queryForFirst(comptDbQueryBuilder.prepare());

                // Genere une ligne de competence global
                LinearLayout competencesView = (LinearLayout) infalInflater.inflate(R.layout.list_class_competences, null);
                competencesView.setId(id);
                TextView titleCompetences = (TextView) competencesView.findViewById(R.id.list_class_competences_title);
                titleCompetences.setText(catComptDb.getTitre() + " :");

                // Parcours la liste des competences
                ForeignCollection<ComptDb> comptDbs = catComptDb.getListCompt();
                for (ComptDb comptDb : comptDbs) {
                    View competenceView = infalInflater.inflate(R.layout.list_class_competence, null);
                    competenceView.getId();

                    TextView titleCompetence = (TextView) competenceView.findViewById(R.id.list_class_competence_title);
                    titleCompetence.setText(comptDb.getTitre() + " :");

                    // Hydrate la valeurs des competences
                    TextView valueCompetence = (TextView) competenceView.findViewById(R.id.list_class_competence_value);
                    Dao<ClassHasComptDb, ?> jobHasComptDbs = Utils.getHelper().getDao(ClassHasComptDb.class);
                    QueryBuilder<ClassHasComptDb, ?> jobHasComptDbQueryBuilder = jobHasComptDbs.queryBuilder();
                    jobHasComptDbQueryBuilder.where().eq(ClassHasComptDb.CLASS, classDb.getId())
                            .and().eq(ClassHasComptDb.COMPT, comptDb.getId());

                    ClassHasComptDb classHasComptDb = jobHasComptDbs.queryForFirst(jobHasComptDbQueryBuilder.prepare());
                    // Si la competences n'est pas trouver, on met la valeur par default
                    if(classHasComptDb == null){
                        valueCompetence.setText(String.valueOf(classHasCatComptDb.getDefaultCout()));
                    } else {
                        valueCompetence.setText(String.valueOf(classHasComptDb.getValue()));
                    }

                    RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.BELOW, id);
                    competenceView.setLayoutParams(params);
                    id++;
                    competenceView.setId(id);
                    competencesView.addView(competenceView);
                }
                convertView.addView(competencesView);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cherche les competences par leur priorité
     *
     * @param principal
     * @return
     * @throws SQLException
     */
    private List<CatComptDb> getCatComptDbs(int principal) throws SQLException {
        Dao<CatComptDb, ?> catComptDbDao = Utils.getHelper().getDao(CatComptDb.class);
        QueryBuilder<CatComptDb, ?> catComptDbQueryBuilder = catComptDbDao.queryBuilder();
        catComptDbQueryBuilder.where().eq(CatComptDb.PRINCIPAL, principal);

        List<CatComptDb> result = catComptDbDao.query(catComptDbQueryBuilder.prepare());
        return result;
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