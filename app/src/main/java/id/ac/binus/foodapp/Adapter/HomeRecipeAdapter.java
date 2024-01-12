package id.ac.binus.foodapp.Adapter;

import android.content.Context;
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
import id.ac.binus.foodapp.R;

public class HomeRecipeAdapter extends RecyclerView.Adapter<HomeRecipeViewHolder>{
    Context context;
    List<Recipe> list;
    RecipeClickListener clickListener;
    public HomeRecipeAdapter(Context context, List<Recipe> list,RecipeClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public HomeRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecipeViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).title);
        holder.tv_title.setSelected(true);
        holder.tv_like.setText(list.get(position).aggregateLikes + " likes");
        holder.tv_serving.setText("Servings for " + list.get(position).servings + " persons");
        holder.tv_time.setText(list.get(position).readyInMinutes + " minutes");
        Picasso.get().load(list.get(position).image).into(holder.iv_recipe);
        holder.tv_healthy_tag.setText("Health Score : "+list.get(position).healthScore);

        holder.list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class HomeRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView list_container;
    TextView tv_title,tv_serving,tv_like,tv_time,tv_healthy_tag;
    ImageView iv_recipe;

    public HomeRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        list_container = itemView.findViewById(R.id.recipe_item);
        tv_title = itemView.findViewById(R.id.tv_title);
        iv_recipe = itemView.findViewById(R.id.iv_recipe);
        tv_serving = itemView.findViewById(R.id.tv_serving);
        tv_like = itemView.findViewById(R.id.tv_like);
        tv_time = itemView.findViewById(R.id.tv_time);
        tv_healthy_tag = itemView.findViewById(R.id.tv_healthy_tag);
    }
}
