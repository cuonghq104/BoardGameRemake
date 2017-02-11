package techkids.cuong.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.myapplication.fragments.BoardGamesRulesFragment;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.events.BackEvent;
import techkids.cuong.myapplication.events.HideToolbarEvent;
import techkids.cuong.myapplication.fragments.BoardGameInformationFragment;
import techkids.cuong.myapplication.fragments.QuestionAndAnswerFragment;
import techkids.cuong.myapplication.models.BoardGame;

public class BoardGameDetailActivity extends AppCompatActivity {


    @BindView(R.id.bottom_bar)
    BottomBar bb;


    @BindView(R.id.iv_boardgame)
    ImageView ivBoardGame;

    private Toolbar toolbar;

//    @BindView(R.id.v_gap)
//    View vGap;

//    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        int position = getIntent().getIntExtra(MainActivity.BOARDGAME_KEY, -1);
//        BoardGame boardGame= (BoardGame) getIntent().getSerializableExtra(MainActivity.BOARDGAME_KEY);
        BoardGame boardGame = BoardGame.boardGamesList.get(position);
//        toolbar.setTitle(boardGame.getName());
        toolbar.setTitle("");
        Picasso.with(ivBoardGame.getContext()).load(boardGame.getImageUrl()).into(ivBoardGame);


        setSupportActionBar(toolbar);
        addListener();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        toolbar.setVisibility(View.GONE);
//        vGap.setVisibility(View.GONE);

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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
////            case android.R.id.home:
////                onBackPressed();
//            default:
//                return this.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rules_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);

//        searchView = (MaterialSearchView) findViewById(R.id.msv);
//
//        searchView.setMenuItem(item);
//
//        searchView.setVisibility(View.GONE);

        return true;
    }

    private void addListener() {
        bb.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_information:
                        changeFragment(new BoardGameInformationFragment(), false);
                        return;
                    case R.id.tab_rules:
                        changeFragment(new BoardGamesRulesFragment(), false);
                        return;
                    case R.id.tab_question:
                        changeFragment(new QuestionAndAnswerFragment(), false);
                        return;
                }
            }
        });
    }

    private void changeFragment(Fragment fragment, boolean addToBackStack) {

//        toolbar.setVisibility(View.VISIBLE);
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

    @Subscribe
    public void hideToolbar(HideToolbarEvent hideToolbarEvent) {
        if (hideToolbarEvent.isHideToolbar()) {
            toolbar.setVisibility(View.GONE);
//            vGap.setVisibility(View.GONE);
//            searchView.setVisibility(View.GONE);
//            searchView.setVisibility(View.GONE);
//            MenuItem item = (MenuItem) findViewById(R.id.action_search);
//            item.setIcon(null);
        } else {
            toolbar.setVisibility(View.VISIBLE);
//            vGap.setVisibility(View.VISIBLE);
//            searchView.setVisibility(View.VISIBLE);
//            searchView.setVisibility(View.VISIBLE);
        }

        if (hideToolbarEvent.isHideBottomNavi()) {
            bb.setVisibility(View.GONE);
        } else {
            bb.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe
    public void backToMainActivity(BackEvent backEvent) {
        this.finish();
    }

    public static class ToDetailActivityEvent{
        //        private BoardGame boardGame;
//
//        public ToDetailActivityEvent(BoardGame boardGame) {
//            this.boardGame = boardGame;
//        }
//
//        public BoardGame getBoardGame() {
//            return boardGame;
//        }
        private int position;

        public ToDetailActivityEvent(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }
}
