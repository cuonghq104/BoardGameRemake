package techkids.cuong.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.viewHolders.CategoryViewHolder;

/**
 * Created by Cuong on 2/6/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private String[] categories;

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_category, parent, false);

        CategoryViewHolder viewHolder = new CategoryViewHolder(view, categories);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public CategoryAdapter(String[] categories) {
        super();
        this.categories = categories;
    }
}
