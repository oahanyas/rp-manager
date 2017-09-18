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

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.query.In;
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
                        playerHasStatsDb.getStatsDb(),
                        playerHasStatsDb.getValue());
            }
            fragmentPlayerStatsButton.setVisibility(View.GONE);
        } else {
            List<StatsDb> stats;
            try {
                stats = Utils.getHelper().getDao(StatsDb.class).queryForAll();

                for (final StatsDb stat : stats) {
                    insertStats(stat);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertStats(StatsDb statsDb) {
        insertStats(statsDb, 0);
    }

    private void insertStats(final StatsDb statsDb, Integer value) {
        View addStat = mLayoutInflater.inflate(R.layout.ll_stat_ligne, null);

        TextView idView = (TextView) addStat.findViewById(R.id.ll_stats_ligne_id);
        idView.setText(String.valueOf(statsDb.getId()));

        TextView typeView = (TextView) addStat.findViewById(R.id.ll_stats_ligne_type);
        typeView.setText(statsDb.getType() + " : ");

        ImageView hint = (ImageView) addStat.findViewById(R.id.ll_stats_ligne_hint);
        hint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), statsDb.getDescription(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

        if(value > 0){
            EditText editText = (EditText) addStat.findViewById(R.id.ll_stats_value);
            editText.setText(String.valueOf(value));
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


        boolean result = true;
        for (int i = 0; i < childCount; i++) {
            View statsLigne = stats.getChildAt(i);

            EditText editText = (EditText) statsLigne.findViewById(R.id.ll_stats_value);
            if(Integer.valueOf(editText.getText().toString()) <= 0){
                result = false;
                break;
            }
        }


        if(result){
            for (int i = 0; i < childCount; i++) {
                PlayerHasStatsDb playerHasStatsDb = new PlayerHasStatsDb();

                View statsLigne = stats.getChildAt(i);
                TextView idView = (TextView) statsLigne.findViewById(R.id.ll_stats_ligne_id);

                try {
                    Dao<StatsDb, Integer> dao = Utils.getHelper().getDao(StatsDb.class);

                    StatsDb statsDb = dao.queryForId(Integer.valueOf(idView.getText().toString()));

                    playerHasStatsDb.setPlayerDb(((MainActivity) getActivity()).getPlayer());
                    playerHasStatsDb.setStatsDb(statsDb);

                    EditText editText = (EditText) statsLigne.findViewById(R.id.ll_stats_value);
                    playerHasStatsDb.setValue(Integer.valueOf(editText.getText().toString()));

                    playerHasStatsDb.save();
                } catch (SQLException e) {
                    result = false;
                    e.printStackTrace();
                }
            }
            if(result){
                getFragmentManager().popBackStack();
            } else {
                //TODO ERRROR
            }
        } else {
            //TODO ERRROR
        }
    }
}
