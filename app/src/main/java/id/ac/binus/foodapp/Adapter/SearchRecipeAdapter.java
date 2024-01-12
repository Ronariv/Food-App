package id.ac.binus.foodapp.Adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.ac.binus.foodapp.Listener.RecipeClickListener;
import id.ac.binus.foodapp.Model.Recipe;
import id.ac.binus.foodapp.Model.Result;
import id.ac.binus.foodapp.R;

public class SearchRecipeAdapter extends RecyclerView.Adapter<SearchRecipeViewHolder> {
    Context context;
    List<Result> list;

    RecipeClickListener clickListener;
    public SearchRecipeAdapter(Context context, List<Result> list, RecipeClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public SearchRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_search,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecipeViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).title);
        holder.tv_title.setSelected(true);
        Picasso.get().load(list.get(position).image).into(holder.iv_recipe);

        holder.list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DETAIL adapter",String.valueOf(list.get(holder.getAdapterPosition()).id));
                clickListener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class SearchRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView list_container;
    TextView tv_title;
    ImageView iv_recipe;

    public SearchRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        list_container = itemView.findViewById(R.id.search_item);
        tv_title = itemView.findViewById(R.id.tv_title);
        iv_recipe = itemView.findViewById(R.id.iv_recipe);
    }
}