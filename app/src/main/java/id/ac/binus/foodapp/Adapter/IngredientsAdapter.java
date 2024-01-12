package id.ac.binus.foodapp.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.ac.binus.foodapp.Model.ExtendedIngredient;
import id.ac.binus.foodapp.R;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_ingredients,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.tv_ingredient_name.setText(list.get(position).name);
        holder.tv_ingredient_name.setSelected(true);
        holder.tv_ingredient_quantity.setText(list.get(position).original);
        holder.tv_ingredient_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.iv_ingredient);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder {
    TextView tv_ingredient_quantity, tv_ingredient_name;
    ImageView iv_ingredient;

    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_ingredient_quantity = itemView.findViewById(R.id.tv_ingredient_quantity);
        tv_ingredient_name = itemView.findViewById(R.id.tv_ingredient_name);
        iv_ingredient = itemView.findViewById(R.id.iv_ingredient);

    }
}

