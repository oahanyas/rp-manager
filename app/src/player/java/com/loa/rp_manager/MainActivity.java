package com.loa.rp_manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.loa.rp_manager.db.PlayerDb;
import com.loa.rp_manager.fragment.InfoPlayer_;
import com.loa.rp_manager.fragment.ListPlayer_;
import com.loa.rp_manager.fragment.StatsPlayer_;
import com.loa.rp_manager.utils.Utils;

import org.androidannotations.annotations.EActivity;

import java.sql.SQLException;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends MainActivityAbstract {

    @Override
    protected void afterViews() {
        super.afterViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<PlayerDb> players = null;
        try {
            players = Utils.getHelper().getDao(PlayerDb.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment newFragment;

        if(players == null || players.size() == 0){
            newFragment = new InfoPlayer_();
            ft.addToBackStack(null);
        } else {
            newFragment = new ListPlayer_();
            ft.addToBackStack(null);
        }

        ft.replace(R.id.fragment_manager, newFragment, newFragment.getClass().getName());
        ft.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment newFragment = null;
        if (id == R.id.menu_player_information) {

        } else if (id == R.id.menu_player_stats) {
            newFragment = new StatsPlayer_();
        } else if (id == R.id.menu_equipment) {

        } else if (id == R.id.menu_inventory) {

        } else if (id == R.id.menu_battle) {

        }

        ft.replace(R.id.fragment_manager, newFragment, newFragment.getClass().getName());
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
