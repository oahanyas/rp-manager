package com.loa.rp_manager.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.loa.rp_manager.R;
import com.loa.rp_manager.adapter.ExpandableListClassAdapter;
import com.loa.rp_manager.db.ClassDb;
import com.loa.rp_manager.utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hanyas on 19/09/2017.
 */
@EFragment(R.layout.fragment_player_list_class)
public class ListClass extends Fragment {

    @ViewById(R.id.fragment_player_list_class_elv)
    protected ExpandableListView expandableListView;

    private ExpandableListClassAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, ClassDb> listDataChild;

    @AfterViews
    protected void afterView(){
        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListClassAdapter(this.getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);

        // Listview Group click listener
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ClassDb>();

        try {
            List<ClassDb> classDbs = Utils.getHelper().getDao(ClassDb.class).queryForAll();

            for (ClassDb classDb : classDbs) {
                listDataHeader.add(classDb.getTitre());
                listDataChild.put(classDb.getTitre(), classDb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
