package techkids.cuong.myapplication.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.myapplication.activities.BoardGameDetailActivity;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.models.BoardGame;
import techkids.cuong.myapplication.models.Paragraph;
import techkids.cuong.myapplication.models.QuestionAndAnswer;

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
        Paragraph.list = Arrays.asList(BoardGame.boardGamesList.get(position).getTutorialBlocks());

        QuestionAndAnswer.questionAndAnswerList = Arrays.asList(QuestionAndAnswer.questionAndAnswersArrays);

        //todo debugging
//        EventBus.getDefault().post(new ChangeFragmentEvent(new BoardGameInformationFragment(), true, position));

//        BoardGame boardGame = BoardGame.boardGamesList.get(position);
        EventBus.getDefault().post(new BoardGameDetailActivity.ToDetailActivityEvent(position));
    }
}
