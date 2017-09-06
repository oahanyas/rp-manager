package com.loa.rp_manager.fragment;

import android.os.Bundle;
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

    private PlayerDb playerDb;

    @AfterViews
    protected void afterView(){
        Bundle bundle = this.getArguments();

        if(bundle != null){
            playerDb = (PlayerDb) bundle.getSerializable("player");

            if(playerDb != null) {
                name.setText(playerDb.getName());
                name.setEnabled(false);

                origin.setText(playerDb.getOrigin());
                origin.setEnabled(false);

                race.setText(playerDb.getRace());
                race.setEnabled(false);

                size.setText(String.valueOf(playerDb.getSize()));
                size.setEnabled(false);

                weight.setText(String.valueOf(playerDb.getWeight()));
                weight.setEnabled(false);

                if(playerDb.getSexe()){
                    sexeMan.setChecked(true);
                } else {
                    sexeWoman.setChecked(true);
                }
                sexe.setEnabled(false);
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

        if(size.getText().toString().equals("")){
            playerDb.setSize(0);
        } else {
            playerDb.setSize(Integer.valueOf(size.getText().toString()));
        }

        if(weight.getText().toString().equals("")){
            playerDb.setWeight(0);
        } else {
            playerDb.setWeight(Integer.valueOf(weight.getText().toString()));
        }

        if(sexeMan.isChecked()){
            playerDb.setSexe(true);
        } else {
            playerDb.setSexe(false);
        }

        try {
            playerDb.save();
            getFragmentManager().popBackStack();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
