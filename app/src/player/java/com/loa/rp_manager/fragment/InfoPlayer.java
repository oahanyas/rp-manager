package com.loa.rp_manager.fragment;

import android.support.v4.app.Fragment;
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
public class InfoPlayer extends Fragment {

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

    @ViewById(R.id.fragment_player_information_size)
    protected EditText size;

    @ViewById(R.id.fragment_player_information_weight)
    protected EditText weight;

    @AfterViews
    protected void afterView(){

    }

    @Click(R.id.fragment_player_information_valider)
    protected void valider(){
        PlayerDb playerDb = new PlayerDb();

        playerDb.setName(name.getText().toString());
        playerDb.setOrigin(origin.getText().toString());
        playerDb.setRace(race.getText().toString());
        playerDb.setSize(Integer.valueOf(size.getText().toString()));
        playerDb.setWeight(Integer.valueOf(weight.getText().toString()));

        if(sexeMan.isChecked()){
            playerDb.setSexe(true);
        } else {
            playerDb.setSexe(false);
        }

        try {
            playerDb.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
