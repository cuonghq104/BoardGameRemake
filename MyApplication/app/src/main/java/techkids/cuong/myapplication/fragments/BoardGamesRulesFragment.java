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

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.adapters.ParagraphAdapter;
import techkids.cuong.myapplication.events.HideToolbarEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoardGamesRulesFragment extends Fragment {


    @BindView(R.id.rv_board_game_rules)
    RecyclerView rvRules;

    @BindView(R.id.msv)
    MaterialSearchView msv;

    private ParagraphAdapter adapter;

    public BoardGamesRulesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new HideToolbarEvent(false));
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
        setupUI();
        return view;
    }


    private void setupUI() {
        adapter = new ParagraphAdapter();
        rvRules.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRules.setAdapter(adapter);
    }

}
