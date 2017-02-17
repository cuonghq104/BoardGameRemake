package techkids.cuong.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.myapplication.CatalogueFullFragment;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.adapters.CatalogueAdapter;
import techkids.cuong.myapplication.events.HideToolbarEvent;
import techkids.cuong.myapplication.models.BoardGame;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoardGameCatalogueFragment extends Fragment {

    @BindView(R.id.ll_coming_soon)
    LinearLayout llComingSoon;

    @BindView(R.id.rv_coming_soon)
    RecyclerView rvComingSoon;

    private CatalogueAdapter adapter;

    @BindView(R.id.ll_hot_games)
    LinearLayout llHotGames;

    @BindView(R.id.rv_hot_games)
    RecyclerView rvHotGames;

    private CatalogueAdapter hotGamesAdapter;

    public BoardGameCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board_game_catalogue, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        EventBus.getDefault().post(new HideToolbarEvent(false));
        return view;
    }

    private List<BoardGame> list;

    private List<BoardGame> hotGamesList;

    private void setupUI() {
        adapter = new CatalogueAdapter();
        list = new ArrayList<>();
        for (int i = 1; i < BoardGame.boardGameArray.length; i++) {
            list.add(BoardGame.boardGameArray[i]);
        }

        adapter.setList(list);
        rvComingSoon.setAdapter(adapter);
        rvComingSoon.setLayoutManager(new StaggeredGridLayoutManager(1, 0));

        hotGamesAdapter = new CatalogueAdapter();

        hotGamesList = new ArrayList<>();

        hotGamesList.add(BoardGame.boardGameArray[0]);

        hotGamesAdapter.setList(hotGamesList);
        rvHotGames.setAdapter(hotGamesAdapter);
        rvHotGames.setLayoutManager(new StaggeredGridLayoutManager(1, 0));
    }

    @OnClick(R.id.ll_coming_soon)
    public void goToCatalogueFullFragment() {
//        EventBus.getDefault().post(new CatalogueFullFragment.CatalogueFullEvent("Coming soon",list ));
        EventBus.getDefault().postSticky(new CatalogueFullFragment.CatalogueFullEvent("Coming soon",list ));
    }

    @OnClick(R.id.ll_hot_games)
    public void onHotGamesClick() {
//        EventBus.getDefault().post(new CatalogueFullFragment.CatalogueFullEvent("Hot games", hotGamesList));
        EventBus.getDefault().postSticky(new CatalogueFullFragment.CatalogueFullEvent("Hot games", hotGamesList));
    }
}
