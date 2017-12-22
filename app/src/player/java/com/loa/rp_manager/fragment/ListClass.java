package com.loa.rp_manager.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ExpandableListView;

import com.loa.rp_manager.R;
import com.loa.rp_manager.adapter.ExpandableListClassAdapter;
import com.loa.rp_manager.db.ClassDb;
import com.loa.rp_manager.utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
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

    private int lastExpandedPosition;

    @AfterViews
    protected void afterView(){
        lastExpandedPosition = -1;

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListClassAdapter(this.getContext(), listDataHeader, listDataChild);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        // setting list adapter
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                expandableListView.setSelectedChild(i, i1, true);
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

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

    @Click(R.id.fragment_player_list_valider)
    protected void validate(){
        long id = expandableListView.getSelectedId();

        if(id != -1){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ListClass_ newFragment = new ListClass_();
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_manager, newFragment, newFragment.getClass().getName());
            ft.commit();
        } else {
            //TODO ERRROR
        }
    }
}
