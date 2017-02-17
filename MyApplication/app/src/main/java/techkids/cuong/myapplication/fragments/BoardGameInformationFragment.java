package techkids.cuong.myapplication.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.adapters.CategoryAdapter;
import techkids.cuong.myapplication.events.BackEvent;
import techkids.cuong.myapplication.events.HideToolbarEvent;
import techkids.cuong.myapplication.models.BoardGame;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoardGameInformationFragment extends Fragment {

//    @BindView(R.id.iv_boardgame)
//    ImageView ivBoardGame;
    //

    @BindView(R.id.iv_boardgame)
    ImageView ivBoardGame;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_number_of_player)
    TextView tvNumberOfPlayer;
    //
    @BindView(R.id.tv_suggested_player)
    TextView tvSuggestedPlayer;
    //
    @BindView(R.id.tv_playing_time)
    TextView tvPlayingTime;

    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;

    @BindView(R.id.rv_play_type) RecyclerView rvPlayType;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    BoardGame boardGame;

    int position = 0;

    public void setPosition(int position) {
        this.position = position;
    }

    public BoardGameInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board_game_information, container, false);
//        YoYo.with(Techniques.ZoomIn)
//                .duration(300)
//                .playOn(getActivity().findViewById(R.id.fl_container));
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().post(new HideToolbarEvent(true, false));
    }

    private CategoryAdapter adapter;

    private CategoryAdapter playTypeAdapter;

    private void setupUI() {


        boardGame = BoardGame.boardGamesList.get(position);

        Picasso.with(ivBoardGame.getContext()).load(boardGame.getImageUrl()).into(ivBoardGame);
//        Picasso.with(ivBoardGame.getContext()).load(boardGame.getImageUrl()).into(ivBoardGame);
//
        String name = boardGame.getName();
//
        tvName.setText(name.toUpperCase());
//
        tvNumberOfPlayer.setText(String.format("%d - %d", boardGame.getMinPlayer(), boardGame.getMaxPlayer()));
//
        tvSuggestedPlayer.setText(String.format("Suggested : %s", boardGame.getFavoritePlayer()));
//
        tvPlayingTime.setText(String.format("%d %s", boardGame.getPlayingTime(), getString(R.string.min)));

        tvDescription.setText(boardGame.getDescription());

        adapter = new CategoryAdapter(boardGame.getCategories());
        rvCategories.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        rvCategories.setAdapter(adapter);

        playTypeAdapter = new CategoryAdapter(boardGame.getPlayType());
        rvPlayType.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        rvPlayType.setAdapter(playTypeAdapter);
    }

//    @OnClick(R.id.bt_back)
//    public void backFromBackStack() {
//        EventBus.getDefault().post(new BackEvent(true));
//    }
}
