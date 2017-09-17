package com.loa.rp_manager;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.loa.rp_manager.db.PlayerDb;
import com.loa.rp_manager.fragment.CreatePlayer_;
import com.loa.rp_manager.fragment.ListPlayer_;
import com.loa.rp_manager.fragment.StatsPlayer_;
import com.loa.rp_manager.utils.Utils;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends MainActivityAbstract {

    private PlayerDb player;

    @ViewById(R.id.nav_view)
    protected NavigationView navigationView;

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
            newFragment = new CreatePlayer_();
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
            newFragment = new CreatePlayer_();
        } else if (id == R.id.menu_player_stats) {
            newFragment = new StatsPlayer_();
        }

        ft.replace(R.id.fragment_manager, newFragment, newFragment.getClass().getName());
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean updateMenu() {
        Boolean value = false;
        if (player != null) {
            value = true;
        }

        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            SubMenu subMen = menu.getItem(i).getSubMenu();

            for (int j = 0; j < subMen.size(); j++) {
                subMen.getItem(j).setEnabled(value);
            }
        }

        return true;
    }

    public void setPlayer(PlayerDb player) {
        this.player = player;
    }

    public PlayerDb getPlayer() {
        return player;
    }
}
