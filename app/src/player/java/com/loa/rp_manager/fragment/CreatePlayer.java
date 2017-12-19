package com.loa.rp_manager.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.loa.rp_manager.MainActivity;
import com.loa.rp_manager.MainActivity_;
import com.loa.rp_manager.MyApplication;
import com.loa.rp_manager.R;
import com.loa.rp_manager.db.PlayerDb;
import com.loa.rp_manager.filter.InputFilterMinMax;

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

    @ViewById(R.id.fragment_player_information_beauty)
    protected EditText beauty;

    @ViewById(R.id.fragment_player_information_sexe)
    protected RadioGroup sexe;

    @ViewById(R.id.fragment_player_information_sexe_man)
    protected RadioButton sexeMan;

    @ViewById(R.id.fragment_player_information_sexe_woman)
    protected RadioButton sexeWoman;

    private PlayerDb playerDb;

    @AfterViews
    protected void afterView(){
        beauty.setFilters(new InputFilter[]{new InputFilterMinMax(1, 10)});

        playerDb = ((MainActivity) getActivity()).getPlayer();
        sexeMan.setChecked(true);

        if(playerDb != null) {
            name.setText(playerDb.getName());
            name.setEnabled(false);

            origin.setText(playerDb.getOrigin());
            origin.setEnabled(false);

            beauty.setText(String.valueOf(playerDb.getBeauty()));
            beauty.setEnabled(false);

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

    @Click(R.id.fragment_player_information_valider)
    protected void valider(){
        if(playerDb == null) {
            playerDb = new PlayerDb();
        }

        playerDb.setName(name.getText().toString());
        playerDb.setBeauty(Integer.valueOf(beauty.getText().toString()));
        playerDb.setOrigin(origin.getText().toString());

        if(sexeMan.isChecked()){
            playerDb.setSexe(true);
        } else {
            playerDb.setSexe(false);
        }

        try {
            playerDb.save();
            ((MainActivity) getActivity()).setPlayer(playerDb);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            StatsPlayer_ newFragment = new StatsPlayer_();
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_manager, newFragment, newFragment.getClass().getName());
            ft.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
