package techkids.cuong.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.models.BoardGame;
import techkids.cuong.myapplication.viewHolders.CatalogueViewHolder;
import techkids.cuong.myapplication.viewHolders.CategoryViewHolder;

/**
 * Created by Cuong on 2/18/2017.
 */

public class CatalogueAdapter extends RecyclerView.Adapter<CatalogueViewHolder> {

    private List<BoardGame> list;

    @Override
    public CatalogueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.layout_board_game_list_item, parent, false);

        CatalogueViewHolder viewHolder = new CatalogueViewHolder(view);

        viewHolder.setList(list);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CatalogueViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<BoardGame> list) {
        this.list = list;
    }
}
