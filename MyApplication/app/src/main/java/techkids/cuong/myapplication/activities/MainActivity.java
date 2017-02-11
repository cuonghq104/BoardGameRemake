package techkids.cuong.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.events.SearchEvent;
import techkids.cuong.myapplication.fragments.BoardGameListFragment;
import techkids.cuong.myapplication.events.ChangeFragmentEvent;
import techkids.cuong.myapplication.models.BoardGame;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String BOARDGAME_NAME_KEY = "boardgame_name";
    public static final String BOARDGAME_KEY = "boardgame";

    @Subscribe
    public void toDetailActivity(BoardGameDetailActivity.ToDetailActivityEvent event){
        Intent intent = new Intent(MainActivity.this, BoardGameDetailActivity.class);
        intent.putExtra(BOARDGAME_KEY, event.getPosition());
        startActivity(intent);
    }


    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @BindView(R.id.cl_root)
    CoordinatorLayout clRoot;

    private Toolbar toolbar;

    @BindView(R.id.sv_boardgames)
    SearchView svBoardGames;

    @BindView(R.id.tv_search)
    TextView tvSearch;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_toggle);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        setupUI();
        addListener();
        changeFragment(new BoardGameListFragment(), false);

    }

    private void setupUI() {
        EditText editText = (EditText) svBoardGames.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editText.setTextColor(getResources().getColor(R.color.colorPrimaryText));
        editText.setHintTextColor(getResources().getColor(R.color.colorSecondaryText));
        editText.setHint(getResources().getString(R.string.search_hint));

        ImageView mag = (ImageView) svBoardGames.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        mag.setVisibility(View.GONE);

    }

    private void addListener() {

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSearch.setVisibility(View.GONE);
                svBoardGames.setIconified(false);
                toggle.setDrawerIndicatorEnabled(false);
                toggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        svBoardGames.setIconified(true);
                        tvSearch.setVisibility(View.VISIBLE);
                        toggle.setDrawerIndicatorEnabled(false);
                        toggle.setHomeAsUpIndicator(R.drawable.ic_toggle);
                    }
                });
            }
        });

        svBoardGames.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                EventBus.getDefault().postSticky(new SearchEvent(newText));
                return false;
            }
        });
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
//        vGap.setVisibility(View.VISIBLE);
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

//        getMenuInflater().inflate(R.menu.rules_menu, menu);
//
//        MenuItem item = menu.findItem(R.id.action_search);
//
//        MaterialSearchView searchView = (MaterialSearchView) findViewById(R.id.msv);
//
//        searchView.setMenuItem(item);
//
//        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
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

}
