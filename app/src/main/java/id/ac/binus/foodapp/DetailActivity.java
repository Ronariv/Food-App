package id.ac.binus.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import id.ac.binus.foodapp.Adapter.IngredientsAdapter;
import id.ac.binus.foodapp.Listener.RecipeDetailResponseListener;
import id.ac.binus.foodapp.Model.RecipeDetailAPIResponse;

public class DetailActivity extends AppCompatActivity {
    int id;
    RecyclerView recyclerView;
    TextView tv_detail_name, tv_detail_source,tv_detail_summary;
    ImageView iv_detail;

    RequestManager manager;
    IngredientsAdapter ingredientsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViews();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailResponseListener,id);
    }

    private void findViews() {
        tv_detail_name = findViewById(R.id.tv_detail_name);
        tv_detail_source = findViewById(R.id.tv_detail_source);
        tv_detail_summary = findViewById(R.id.tv_detail_summary);

        iv_detail = findViewById(R.id.iv_detail);
        recyclerView = findViewById(R.id.rv_detail_ingredients);
    }

    private final RecipeDetailResponseListener recipeDetailResponseListener = new RecipeDetailResponseListener() {
        @Override
        public void didFetch(RecipeDetailAPIResponse response, String message) {
            tv_detail_name.setText(response.title);
            tv_detail_source.setText(response.sourceName);

            String summary = response.summary;
            summary = summary.replaceAll("<a href=", "");
            summary = summary.replaceAll("</a>", "");
            summary = summary.replaceAll("<b>", "");
            summary = summary.replaceAll("</b>", "");
            summary = summary.replaceAll(">", "");

            tv_detail_summary.setText(summary);
            Picasso.get().load(response.image).into(iv_detail);

//            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL,false));

            ingredientsAdapter = new IngredientsAdapter(DetailActivity.this, response.extendedIngredients);
            recyclerView.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}