package com.loa.rp_manager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.ForeignCollection;
import com.loa.rp_manager.MainActivity;
import com.loa.rp_manager.R;
import com.loa.rp_manager.db.PlayerDb;
import com.loa.rp_manager.db.PlayerHasStatsDb;
import com.loa.rp_manager.db.StatsDb;
import com.loa.rp_manager.utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Hanyas on 04/09/2017.
 */
@EFragment(R.layout.fragment_player_stats)
public class StatsPlayer extends Fragment {

    @ViewById(R.id.fragment_player_stats)
    protected LinearLayout stats;

    @ViewById(R.id.fragment_player_stats_button)
    protected Button fragmentPlayerStatsButton;

    private LayoutInflater mLayoutInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater = inflater;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @AfterViews
    protected void afterView() {
        PlayerDb player = ((MainActivity) getActivity()).getPlayer();

        ForeignCollection<PlayerHasStatsDb> playerHasStatsDbs = player.getPlayerHasStatsDb();
        if(playerHasStatsDbs != null && playerHasStatsDbs.size() > 0 ){
            Iterator<PlayerHasStatsDb> iterator = playerHasStatsDbs.iterator();
            while(iterator.hasNext()){
                final PlayerHasStatsDb playerHasStatsDb = iterator.next();

                insertStats(
                        playerHasStatsDb.getStatsDb().getType(),
                        playerHasStatsDb.getStatsDb().getDescription(),
                        playerHasStatsDb.getValue());
            }
            fragmentPlayerStatsButton.setVisibility(View.GONE);
        } else {
            List<StatsDb> stats;
            try {
                stats = Utils.getHelper().getDao(StatsDb.class).queryForAll();

                for (final StatsDb stat : stats) {
                    insertStats(stat.getType(), stat.getDescription());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertStats(String type, String description) {
        insertStats(type, description, 0);
    }

    private void insertStats(String type, final String desc, Integer value) {
        View addStat = mLayoutInflater.inflate(R.layout.ll_stat_ligne, null);
        final TextView description = (TextView) addStat.findViewById(R.id.ll_stats_ligne_description);
        description.setText(type + " : ");

        ImageView hint = (ImageView) addStat.findViewById(R.id.ll_stats_ligne_hint);
        hint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), desc, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        if(value > 0){
            EditText editText = (EditText) addStat.findViewById(R.id.ll_stats_value);
            editText.setText(value);
            editText.setEnabled(false);

            ImageView imageView = (ImageView) addStat.findViewById(R.id.ll_stats_add);
            if (false) {
                imageView.setEnabled(false);
            } else {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        }

        stats.addView(addStat);
    }

    @Click(R.id.fragment_player_stats_button)
    protected void saveStats(){
        final int childCount = stats.getChildCount();

        for (int i = 0; i < childCount; i++) {
            PlayerHasStatsDb playerHasStatsDb = new PlayerHasStatsDb();

            View v = stats.getChildAt(i);

            try {
                playerHasStatsDb.save();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
