package techkids.cuong.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.MenuInflater;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.myapplication.CatalogueFullFragment;
import techkids.cuong.myapplication.LoginActivity;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.events.HideToolbarEvent;
import techkids.cuong.myapplication.events.LoginEvent;
import techkids.cuong.myapplication.events.SearchEvent;
import techkids.cuong.myapplication.fragments.BoardGameCatalogueFragment;
import techkids.cuong.myapplication.fragments.BoardGameListFragment;
import techkids.cuong.myapplication.events.ChangeFragmentEvent;
import techkids.cuong.myapplication.fragments.SignUpFragment;
import techkids.cuong.myapplication.models.BoardGame;
import techkids.cuong.myapplication.models.User;
import techkids.cuong.myapplication.transforms.CircleTransform;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String BOARDGAME_NAME_KEY = "boardgame_name";
    public static final String BOARDGAME_KEY = "boardgame";

    @Subscribe
    public void toDetailActivity(BoardGameDetailActivity.ToDetailActivityEvent event){
        Intent intent = new Intent(MainActivity.this, BoardGameDetailActivity.class);
        intent.putExtra(BOARDGAME_KEY, event.getBoardGame());
        if (!event.getBoardGame().getName().equals("Werewolf basic - a very basic game")) {
            Toast.makeText(MainActivity.this, "Dang phat trien", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event) {
//        Picasso.with(this).load(User.getProfile().getProfilePictureUri(50, 50)).into(ivProfile);
//        tvName.setText(User.getProfile().getName());
        changeFragment(new BoardGameCatalogueFragment(), false);
        Picasso.with(this).load(User.getProfile().getProfilePictureUri(80, 80)).transform(new CircleTransform()).into(ivProfile);
        tvName.setText(User.getProfile().getName());
        tvMail.setText(User.userName);
        Toast.makeText(this, User.getProfile().getName(), Toast.LENGTH_SHORT).show();
    }

    @BindView(R.id.rl_container)
    RelativeLayout rlContainer;

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

    @BindView(R.id.nav_view)
    NavigationView navView;

    View headerView;

    private ImageView ivProfile;

    private TextView tvName;

    private TextView tvMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        headerView = navView.getHeaderView(0);
        ivProfile = (ImageView) headerView.findViewById(R.id.iv_profile);
        tvName = (TextView) headerView.findViewById(R.id.tv_name);
        tvMail = (TextView) headerView.findViewById(R.id.tv_mail);
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

        setupUI();
        rlContainer.setVisibility(View.GONE);
        addListener();
        changeFragment(new SignUpFragment(), false);
    }

    @Subscribe
    public void goToFullCatalogueFragment(CatalogueFullFragment.CatalogueFullEvent event) {
        changeFragment(new CatalogueFullFragment(), true);
    }
    @Subscribe
    public void onHideToolbarEvent (HideToolbarEvent event) {
        if (event.isHideToolbar()) {
            rlContainer.setVisibility(View.GONE);
        } else {
            rlContainer.setVisibility(View.VISIBLE);
        }
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

        tvSearch = (TextView) findViewById(R.id.tv_search);
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

        svBoardGames.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
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
        getMenuInflater().inflate(R.menu.main, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        MenuItem item = menu.findItem(R.id.it_search);

        SearchView searchView = (SearchView) item.getActionView();
//
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
