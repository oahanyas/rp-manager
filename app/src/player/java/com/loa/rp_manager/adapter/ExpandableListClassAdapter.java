package com.loa.rp_manager.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.loa.rp_manager.R;
import com.loa.rp_manager.db.ClassDb;

public class ExpandableListClassAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ClassDb> _listDataChild;

    public ExpandableListClassAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, ClassDb> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
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

        final ClassDb classDb = getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_class, null);
        }

        TextView desc = (TextView) convertView.findViewById(R.id.list_item_class_description);
        desc.setText(classDb.getDescription());

        TextView achetype = (TextView) convertView.findViewById(R.id.list_item_class_achetype);
        achetype.setText(classDb.getAchetype());

        TextView multi = (TextView) convertView.findViewById(R.id.list_item_class_multi);
        multi.setText(String.valueOf(classDb.getMultiplicateuPv()));

        TextView pv = (TextView) convertView.findViewById(R.id.list_item_class_pv);
        pv.setText(String.valueOf(classDb.getPv()));

        TextView init = (TextView) convertView.findViewById(R.id.list_item_class_init);
        init.setText(String.valueOf(classDb.getInitiative()));

        TextView runeInne = (TextView) convertView.findViewById(R.id.list_item_class_rune_inne);
        runeInne.setText(String.valueOf(classDb.getRuneInne()));

        return convertView;
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