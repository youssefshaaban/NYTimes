package com.smartzone.technology.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.smartzone.technology.R;
import com.smartzone.technology.databinding.ActivityHomeActvityBinding;
import com.smartzone.technology.ui.fragment.home.HomeFragment;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;

public class HomeActvity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    ActivityHomeActvityBinding binding;
    private static final String BACK_STACK_ROOT_TAG = HomeFragment.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home_actvity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager=getSupportFragmentManager();
        binding.navView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(binding.navView.getMenu().getItem(0));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_actvity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;
        if (id == R.id.nav_home) {
            fragment=new HomeFragment();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (fragment!=null){
            if (fragmentManager.findFragmentByTag(fragment.getClass().getName()) == null || !fragmentManager.findFragmentByTag(fragment.getClass().getName()).isVisible()) {
                addFragmentMenu(fragment);
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addFragmentMenu(Fragment fragment) {
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment, fragment.getClass().getName())
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Fragment homeFrag = fragmentManager.findFragmentByTag(BACK_STACK_ROOT_TAG);
            if (fragmentManager.getBackStackEntryCount() > 1) {
                // We have fragments on the backstack that are poppable
                fragmentManager.popBackStackImmediate();
            } else if ((homeFrag == null || !homeFrag.isVisible()) && fragmentManager.getBackStackEntryCount() == 1) {
                // We aren't showing the home screen, so that is the next stop on the back journey
                binding.navView.getMenu().getItem(0).setChecked(true);
                onNavigationItemSelected(binding.navView.getMenu().getItem(0));
            } else {
                // We are already showing the home screen, so the next stop is out of the app.
                supportFinishAfterTransition();
            }
        }
    }
}
