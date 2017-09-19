package com.loa.rp_manager.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loa.rp_manager.MainActivity;
import com.loa.rp_manager.R;
import com.loa.rp_manager.db.PlayerDb;
import com.loa.rp_manager.utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Hanyas on 06/09/2017.
 */
@EFragment(R.layout.fragment_player_list)
public class ListPlayer extends Fragment {

    @ViewById(R.id.fragment_player_list)
    protected LinearLayout tableLayout;

    private LayoutInflater mLayoutInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater = inflater;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @AfterViews
    protected void afterView(){
        ((MainActivity) getActivity()).updateMenu();

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        List<PlayerDb> players = null;
        try {
            players = Utils.getHelper().getDao(PlayerDb.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (final PlayerDb player : players) {
            View addPlayer = mLayoutInflater.inflate(R.layout.row_player_list, null);
            addPlayer.setLayoutParams(new ViewGroup.LayoutParams(size.x, ViewGroup.LayoutParams.WRAP_CONTENT));

            ((TextView) addPlayer.findViewById(R.id.row_player_list_name)).setText(player.getName());
            ((TextView) addPlayer.findViewById(R.id.row_player_list_level)).setText(String.valueOf(player.getLvl()));
            addPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) getActivity()).setPlayer(player);
                    ((MainActivity) getActivity()).updateMenu();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment newFragment = new StatsPlayer_();

                    ft.addToBackStack(null);
                    ft.replace(R.id.fragment_manager, newFragment, newFragment.getClass().getName());
                    ft.commit();
                }
            });

            tableLayout.addView(addPlayer,
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));

        }
    }

    @Click(R.id.fragment_player_list_button)
    protected void newPlayer(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment newFragment = new CreatePlayer_();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_manager, newFragment, newFragment.getClass().getName());
        ft.commit();
    }
}
