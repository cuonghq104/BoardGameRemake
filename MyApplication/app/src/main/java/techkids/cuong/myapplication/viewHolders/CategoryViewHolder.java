package techkids.cuong.myapplication.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.myapplication.R;

/**
 * Created by Cuong on 2/6/2017.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder{

    private String[] categories;

    @BindView(R.id.tv_category)
    TextView tvCategory;

    public CategoryViewHolder(View itemView, String[] categories) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.categories = categories;
    }

    public void bind(int position) {
        tvCategory.setText(categories[position]);
    }

}
