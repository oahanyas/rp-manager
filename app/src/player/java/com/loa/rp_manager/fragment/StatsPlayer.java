package com.loa.rp_manager.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import com.loa.rp_manager.R;
import com.loa.rp_manager.utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Map;

/**
 * Created by Hanyas on 04/09/2017.
 */
@EFragment(R.layout.fragment_player_stats)
public class StatsPlayer extends Fragment {

    @ViewById(R.id.fragment_player_stats)
    protected LinearLayout stats;

    @AfterViews
    protected void afterView() {
        Map<String, String> statsMap = Utils.getHashMapResource(R.xml.stats);

        for(Map.Entry<String, String> entry : statsMap.entrySet()) {
            String key = entry.getKey();
            final String value = entry.getValue();

            View addStat = getActivity().findViewById(R.id.ll_stats_ligne);
            TextView description = (TextView) addStat.findViewById(R.id.ll_stats_ligne_description);
            description.setText(key + " : ");

            ImageView hint = (ImageView) getActivity().findViewById(R.id.ll_stats_ligne_hint);
            hint.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(v.getContext(), value, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            stats.addView(addStat);
        }
    }
}
