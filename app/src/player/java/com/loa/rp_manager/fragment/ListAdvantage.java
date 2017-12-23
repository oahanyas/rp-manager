package com.loa.rp_manager.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.loa.rp_manager.MainActivity;
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
@EFragment(R.layout.fragment_player_list_avantage)
public class ListAdvantage extends Fragment {

    @ViewById(R.id.player_list_avantage_list)
    protected LinearLayout LinearLayoutAvantageList;

    @AfterViews
    protected void afterView(){

    }
}
