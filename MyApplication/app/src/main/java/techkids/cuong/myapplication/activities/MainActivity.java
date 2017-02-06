package techkids.cuong.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.events.SearchEvent;
import techkids.cuong.myapplication.fragments.BoardGameListFragment;
import techkids.cuong.myapplication.events.ChangeFragmentEvent;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String BOARDGAME_NAME_KEY = "boardgame_name";
    public static final String BOARDGAME_KEY = "boardgame";

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @BindView(R.id.cl_root)
    CoordinatorLayout clRoot;

    private Toolbar toolbar;

    @BindView(R.id.v_gap)
    View vGap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        changeFragment(new BoardGameListFragment(), false);
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void changeFragment(Fragment fragment, boolean addToBackStack) {

        toolbar.setVisibility(View.VISIBLE);
        vGap.setVisibility(View.VISIBLE);
        if (addToBackStack)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .addToBackStack(null)
                    .commit();
        else
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//         Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.detail_menu, menu);
//        MenuItem item = menu.findItem(R.id.it_search);
//
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                EventBus.getDefault().post(new SearchEvent(newText));
//                return false;
//            }
//        });

        getMenuInflater().inflate(R.menu.rules_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);

        MaterialSearchView searchView = (MaterialSearchView) findViewById(R.id.msv);

        searchView.setMenuItem(item);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                EventBus.getDefault().post(new SearchEvent(newText));
                return false;
            }
        });


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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Subscribe
    public void toDetailActivity(BoardGameDetailActivity.ToDetailActivityEvent event){
        Intent intent = new Intent(MainActivity.this, BoardGameDetailActivity.class);
        intent.putExtra(BOARDGAME_KEY, event.getPosition());
//        intent.putExtra(BOARDGAME_NAME_KEY,event.getBoardGame().getName());
        startActivity(intent);
    }
    @Subscribe
    public void goToDetailFragment(ChangeFragmentEvent changeFragmentEvent) {

//        Intent intent = new Intent(MainActivity.this, BoardGameDetailActivity.class);
//        intent.putExtra(BOARDGAME_NAME_KEY,)
//        startActivity(intent);

//        int boardgame = changeFragmentEvent.getPosition();
//
//
//        if (!BoardGame.boardGamesList.get(boardgame).getName().equals("Werewolf basic")) {
//            Snackbar snackbar = Snackbar.make(clRoot, "Đang phát triển", Snackbar.LENGTH_SHORT);
//            snackbar.show();
//            return;
//        }
//
//        Bundle bundle = new Bundle();
//
//        bundle.putInt(BoardGame.BOARD_GAME, boardgame);
//
//        Fragment fragment = changeFragmentEvent.getFragment();
//
//        fragment.setArguments(bundle);
//
////        toolbar.setVisibility(View.GONE);
////        vGap.setVisibility(View.GONE);
//
//        changeFragment(fragment, changeFragmentEvent.isAddToBackStack());
    }



}
