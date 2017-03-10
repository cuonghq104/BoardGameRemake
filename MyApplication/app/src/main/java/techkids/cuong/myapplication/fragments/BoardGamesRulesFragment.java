package techkids.cuong.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.adapters.ParagraphAdapter;
import techkids.cuong.myapplication.events.BoardGameEvent;
import techkids.cuong.myapplication.events.HideToolbarEvent;
import techkids.cuong.myapplication.models.BoardGame;
import techkids.cuong.myapplication.models.Paragraph;
import us.feras.mdv.MarkdownView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoardGamesRulesFragment extends Fragment {


    @BindView(R.id.rv_rules)
    RecyclerView rvRules;

//    @BindView(R.id.msv)
//    MaterialSearchView msv;

    private ParagraphAdapter adapter;

//    @BindView(R.id.markdown_view)
//    MarkdownView markdownView;

    BoardGame boardgame;

    public BoardGamesRulesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
//<<<<<<< HEAD
//        EventBus.getDefault().post(new HideToolbarEvent(false, false));
//
//=======
//        EventBus.getDefault().post(new HideToolbarEvent(false));
//>>>>>>> Add publisher card, add catalogue fragment
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board_games_rules, container, false);
        ButterKnife.bind(this, view);
//        boardgame = BoardGame.boardGamesList.get(0);
        setupUI();
        return view;
    }


    private void setupUI() {

//        markdownView.loadMarkdown(boardgame.getRules(), "file:///android_asset/classic.css");

//        WebSettings webSettings = markdownView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        markdownView.loadMarkdown();
        adapter = new ParagraphAdapter();
        rvRules.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRules.setAdapter(adapter);
    }

    @Subscribe
    public void onSearch(RuleSearchEvent event) {
        String querry = event.getQuerry();

    }

    @Subscribe(sticky =  true)
    public void onBoardGameEvent(BoardGameEvent event) {
        this.boardgame = event.getBoardGame();
        Paragraph.list = Arrays.asList(boardgame.getRuleParagraphs());
        setupUI();
    }
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        if (markdownView != null) {
//            markdownView.findAllAsync(query);
//        }
//
//        return false;
//    }

//    @Override
//    public void onSearchViewClosed() {
//        if (markdownView != null) {
//            markdownView.clearMatches();
//        }
//
//    }



    public static class RuleSearchEvent {
        private String querry;

        public RuleSearchEvent(String querry) {
            this.querry = querry;
        }

        public String getQuerry() {
            return querry;
        }
    }


}
