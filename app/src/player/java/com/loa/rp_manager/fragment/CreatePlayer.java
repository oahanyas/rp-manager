package com.loa.rp_manager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.loa.rp_manager.R;
import com.loa.rp_manager.db.PlayerDb;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;

/**
 * Created by Hanyas on 06/09/2017.
 */
@EFragment(R.layout.fragment_player_information)
public class CreatePlayer extends Fragment {

    @ViewById(R.id.fragment_player_information_name)
    protected EditText name;

    @ViewById(R.id.fragment_player_information_origin)
    protected EditText origin;

    @ViewById(R.id.fragment_player_information_race)
    protected EditText race;

    @ViewById(R.id.fragment_player_information_sexe)
    protected RadioGroup sexe;

    @ViewById(R.id.fragment_player_information_sexe_man)
    protected RadioButton sexeMan;

    @ViewById(R.id.fragment_player_information_sexe_woman)
    protected RadioButton sexeWoman;

    private PlayerDb playerDb;

    @AfterViews
    protected void afterView(){
        Bundle bundle = this.getArguments();

        sexeMan.setChecked(true);
        if(bundle != null){
            playerDb = (PlayerDb) bundle.getSerializable("player");

            if(playerDb != null) {
                name.setText(playerDb.getName());
                name.setEnabled(false);

                origin.setText(playerDb.getOrigin());
                origin.setEnabled(false);

                race.setText(playerDb.getRace());
                race.setEnabled(false);

                if(playerDb.getSexe()){
                    sexeMan.setChecked(true);
                } else {
                    sexeWoman.setChecked(true);
                }
                sexe.setEnabled(false);
                sexeMan.setEnabled(false);
                sexeWoman.setEnabled(false);
            }
        }
    }

    @Click(R.id.fragment_player_information_valider)
    protected void valider(){
        if(playerDb == null) {
            playerDb = new PlayerDb();
        }

        playerDb.setName(name.getText().toString());
        playerDb.setRace(race.getText().toString());
        playerDb.setOrigin(origin.getText().toString());

        if(sexeMan.isChecked()){
            playerDb.setSexe(true);
        } else {
            playerDb.setSexe(false);
        }

        try {
            playerDb.save();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ListPlayer_ newFragment = new ListPlayer_();
            ft.replace(R.id.fragment_manager, newFragment, newFragment.getClass().getName());
            ft.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
