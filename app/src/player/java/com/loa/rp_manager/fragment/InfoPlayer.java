package com.loa.rp_manager.fragment;

import android.support.v4.app.Fragment;

import com.loa.rp_manager.R;
import com.loa.rp_manager.db.PlayerDb;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Hanyas on 06/09/2017.
 */
@EFragment(R.layout.fragment_player_information)
public class InfoPlayer extends Fragment {

    @AfterViews
    protected void afterView(){

    }

    @Click(R.id.fragment_player_information_valider)
    protected void valider(){
        PlayerDb playerDb = new PlayerDb();

        playerDb.setName();
        playerDb.setOrigin();
        playerDb.setRace();
        playerDb.setSexe();
    }
}
