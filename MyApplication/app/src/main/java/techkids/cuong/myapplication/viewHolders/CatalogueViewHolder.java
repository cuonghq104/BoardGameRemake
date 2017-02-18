package techkids.cuong.myapplication.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.activities.BoardGameDetailActivity;
import techkids.cuong.myapplication.models.BoardGame;
import techkids.cuong.myapplication.models.Paragraph;
import techkids.cuong.myapplication.models.QuestionAndAnswer;

/**
 * Created by Cuong on 2/18/2017.
 */
public class CatalogueViewHolder extends RecyclerView.ViewHolder{

    private List<BoardGame> list;

    public void setList(List<BoardGame> list) {
        this.list = list;
    }

    public CatalogueViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    BoardGame boardGame;

    @BindView(R.id.iv_boardgame)
    ImageView ivBoardGame;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_players)
    TextView tvPlayers;

    @BindView(R.id.tv_playing_time)
    TextView tvPlayingTime;

    public void bind(int position) {
        this.boardGame = list.get(position);

        Picasso.with(ivBoardGame.getContext())
                .load(boardGame.getThumbUrl())
                .placeholder(R.drawable.default_placeholder)
                .into(ivBoardGame);

        tvName.setText(boardGame.getName());

        tvPlayingTime.setText(String.format("%d min",boardGame.getPlayingTime()));

        tvPlayers.setText(String.format("%d - %d", boardGame.getMinPlayer(), boardGame.getMaxPlayer()));
    }

    @OnClick(R.id.iv_boardgame)
    public void changeDetailFragment() {


            QuestionAndAnswer.questionAndAnswerList = Arrays.asList(QuestionAndAnswer.questionAndAnswersArrays);

        //todo debugging
//        EventBus.getDefault().post(new ChangeFragmentEvent(new BoardGameInformationFragment(), true, position));

//        BoardGame boardGame = BoardGame.boardGamesList.get(position);
        EventBus.getDefault().post(new BoardGameDetailActivity.ToDetailActivityEvent(boardGame));

    }
}
