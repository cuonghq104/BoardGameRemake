package techkids.cuong.myapplication.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.fragments.BoardGameInformationFragment;
import techkids.cuong.myapplication.events.ChangeFragmentEvent;
import techkids.cuong.myapplication.models.BoardGame;

/**
 * Created by Cuong on 1/5/2017.
 */
public class BoardGameViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.iv_boardgame)
    ImageView ivBoardGame;

    @BindView(R.id.tv_name)
    TextView tvName;

    int position;

    public BoardGameViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position) {
        this.position = position;

        BoardGame boardGame = BoardGame.boardGamesList.get(position);

        tvName.setText(boardGame.getName());

        Picasso.with(ivBoardGame.getContext()).load(boardGame.getThumbUrl()).into(ivBoardGame);
    }

    @OnClick(R.id.iv_boardgame)
    public void changeDetailFragment() {
        EventBus.getDefault().post(new ChangeFragmentEvent(new BoardGameInformationFragment(), true, position));
    }
}