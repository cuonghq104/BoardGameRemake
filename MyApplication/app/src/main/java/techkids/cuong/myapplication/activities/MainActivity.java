package techkids.cuong.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
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
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.facebook.login.widget.ProfilePictureView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.myapplication.fragments.CatalogueFullFragment;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.events.HideToolbarEvent;
import techkids.cuong.myapplication.events.LoginEvent;
import techkids.cuong.myapplication.events.SearchEvent;
import techkids.cuong.myapplication.fragments.BoardGameCatalogueFragment;
import techkids.cuong.myapplication.fragments.SearchResultFragment;
import techkids.cuong.myapplication.fragments.SignUpFragment;
import techkids.cuong.myapplication.utils.DBContext;
import techkids.cuong.myapplication.models.BoardGame;
import techkids.cuong.myapplication.models.BoardgameSuggestion;
import techkids.cuong.myapplication.models.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        ,FragmentManager.OnBackStackChangedListener{

    public static final String BOARDGAME_NAME_KEY = "boardgame_name";
    public static final String BOARDGAME_ID_KEY = "boardgame_ID";
    private static final String TAG = MainActivity.class.toString();


//    @BindView(R.id.rl_container)
//    RelativeLayout rlContainer;

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

//    @BindView(R.id.cl_root)
//    CoordinatorLayout clRoot;

    private Toolbar toolbar;

//    @BindView(R.id.sv_boardgames)
//    SearchView svBoardGames;

//    @BindView(R.id.tv_search)
//    TextView tvSearch;

    private ActionBarDrawerToggle toggle;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;
    View headerView;

    private ProfilePictureView ivProfile;

    private TextView tvName;

    private TextView tvMail;

    @BindView(R.id.appbar)
    AppBarLayout mAppBar;

    String mLastQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getReferences();
        DBContext.getInstance().putBoardGameList(BoardGame.boardGamesList);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_board_game);
        floatingSearchView.attachNavigationDrawerToMenuButton(drawerLayout);

        //todo code for not using floatingSerchView
//        setSupportActionBar(toolbar);


//        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        toggle.setDrawerIndicatorEnabled(false);
//        toggle.setHomeAsUpIndicator(R.drawable.ic_toggle);
//        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawer.openDrawer(Gravity.LEFT);
//            }
//        });
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        setupUI();
        addListener();

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                floatingSearchView.setTranslationY(verticalOffset);
            }
        });

        //todo no need login first
//        changeFragment(new SignUpFragment(), false);
        changeFragment(new BoardGameCatalogueFragment(),false);
    }

    @Subscribe
    public void toDetailActivity(BoardGameDetailActivity.ToDetailActivityEvent event) {
        Intent intent = new Intent(MainActivity.this, BoardGameDetailActivity.class);

        intent.putExtra(BOARDGAME_ID_KEY, event.getBoardGameId());

        //// TODO: 3/12/2017 need to change
        if (!DBContext.getInstance().getBoardGameById(event.getBoardGameId())
                .getId().equals("000001")) {
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
        try {
            tvName.setText(event.getGraphResponse().getJSONObject().getString("name"));
            tvMail.setText(event.getGraphResponse().getJSONObject().getString("email"));
            ivProfile.setPresetSize(ProfilePictureView.NORMAL);
            ivProfile.setProfileId(event.getGraphResponse().getJSONObject().getString("id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, User.getProfile().getName(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void goToFullCatalogueFragment(CatalogueFullFragment.CatalogueFullEvent event) {
        changeFragment(new CatalogueFullFragment(), true);
    }

    @Subscribe
    public void onHideToolbarEvent(HideToolbarEvent event) {
//        if (event.isHideToolbar()) {
//            rlContainer.setVisibility(View.GONE);
//        } else {
//            rlContainer.setVisibility(View.VISIBLE);
//        }
    }

    private void setupUI() {


        //        rlContainer.setVisibility(View.GONE);


//        EditText editText = (EditText) svBoardGames.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        editText.setTextColor(getResources().getColor(R.color.colorPrimaryText));
//        editText.setHintTextColor(getResources().getColor(R.color.colorSecondaryText));
//        editText.setHint(getResources().getString(R.string.search_hint));

//        ImageView mag = (ImageView) svBoardGames.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
//        mag.setVisibility(View.GONE);

    }

    private void addListener() {

        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                floatingSearchView.swapSuggestions(Arrays.asList(new BoardgameSuggestion()));
            }
        });
        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                mLastQuery = currentQuery;
                Log.d(TAG, "onSearchAction:");
                changeFragment(SearchResultFragment.create(currentQuery),true);
            }
        });

        floatingSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
//                fadeDimBackground(0, 150, null);
            }

            @Override
            public void onFocusCleared() {
//                floatingSearchView.setSearchBarTitle(mLastQuery);

                Log.d(TAG, "onFocusCleared: ");
            }
        });

        getSupportFragmentManager().addOnBackStackChangedListener(this);
//        tvSearch = (TextView) findViewById(R.id.tv_search);
//        tvSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvSearch.setVisibility(View.GONE);
//                svBoardGames.setIconified(false);
//                toggle.setDrawerIndicatorEnabled(false);
//                toggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
//                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        svBoardGames.setIconified(true);
//                        tvSearch.setVisibility(View.VISIBLE);
//                        toggle.setDrawerIndicatorEnabled(false);
//                        toggle.setHomeAsUpIndicator(R.drawable.ic_toggle);
//                    }
//                });
//            }
//        });
//
//        svBoardGames.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                EventBus.getDefault().postSticky(new SearchEvent(newText));
//                return false;
//            }
//        });

    }



    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void changeFragment(Fragment fragment, boolean addToBackStack) {

//        toolbar.setVisibility(View.VISIBLE);
//        vGap.setVisibility(View.VISIBLE);
        if (addToBackStack)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .setCustomAnimations(android.R.anim.fade_in
                            ,android.R.anim.fade_out
                            ,android.R.anim.fade_in
                            ,android.R.anim.fade_out)
                    .addToBackStack(null)
                    .commit();
        else
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .setCustomAnimations(android.R.anim.fade_in
                            ,android.R.anim.fade_out
                            ,android.R.anim.fade_in
                            ,android.R.anim.fade_out)
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

        //// TODO: 3/13/2017  wtf is this shit ???
//        getMenuInflater().inflate(R.menu.main, menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.detail_menu, menu);
//        MenuItem item = menu.findItem(R.id.it_search);
//
//        SearchView searchView = (SearchView) item.getActionView();
////
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

        //// TODO: 3/13/2017 this shit ends here

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
        drawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (id == R.id.nav_board_game) {
            // Handle the camera action
            changeFragment(new BoardGameCatalogueFragment(),false);
        } else if (id == R.id.nav_coming_soon) {
            //todo debugging
            EventBus.getDefault().postSticky(new CatalogueFullFragment.CatalogueFullEvent("Coming soon",BoardGame.comingSoonList ));
        } else if (id == R.id.nav_strategy) {
            EventBus.getDefault().postSticky(new CatalogueFullFragment.CatalogueFullEvent("Strategy",BoardGame.strategyList));
        } else if (id == R.id.nav_family) {
            EventBus.getDefault().postSticky(new CatalogueFullFragment.CatalogueFullEvent("Family",BoardGame.familyList));
        } else if (id == R.id.nav_hot) {
            ArrayList<BoardGame> hotGamesList = new ArrayList<>();

            hotGamesList.add(BoardGame.boardGameArray[0]);

            EventBus.getDefault().postSticky(new CatalogueFullFragment.CatalogueFullEvent("Hot games",hotGamesList));

        } else if (id == R.id.nav_settings) {
            Snackbar.make(flContainer, "Đang phát triển", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about_us) {
            Snackbar.make(flContainer, "Đang phát triển", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_log_out) {
            changeFragment(new SignUpFragment(), false);
        }

        return true;
    }

    public void getReferences() {
        headerView = navigationView.getHeaderView(0);
        ivProfile = (ProfilePictureView) headerView.findViewById(R.id.iv_profile);
        tvName = (TextView) headerView.findViewById(R.id.tv_name);
        tvMail = (TextView) headerView.findViewById(R.id.tv_mail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    @Override
    public void onBackStackChanged() {

        int backStackEntryCount =  getSupportFragmentManager().getBackStackEntryCount();
        Log.d(TAG, "onBackStackChanged: backStackEntryCount = "+ backStackEntryCount);
        if (backStackEntryCount > 0) {
            floatingSearchView.setLeftActionMode(FloatingSearchView.LEFT_ACTION_MODE_SHOW_HOME);
        }else{
            floatingSearchView.setLeftActionMode(FloatingSearchView.LEFT_ACTION_MODE_SHOW_HAMBURGER);
        }
    }
//    private void fadeDimBackground(int from, int to, Animator.AnimatorListener listener) {
//        ValueAnimator anim = ValueAnimator.ofInt(from, to);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                int value = (Integer) animation.getAnimatedValue();
//                mDimDrawable.setAlpha(value);
//            }
//        });
//        if(listener != null) {
//            anim.addListener(listener);
//        }
//        anim.setDuration(ANIM_DURATION);
//        anim.start();
//    }
}
