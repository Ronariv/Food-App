package id.ac.binus.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.binus.foodapp.Adapter.SearchRecipeAdapter;
import id.ac.binus.foodapp.Listener.RecipeClickListener;
import id.ac.binus.foodapp.Listener.SearchRecipeResponseListener;
import id.ac.binus.foodapp.Model.HomeRecipeAPIResponse;
import id.ac.binus.foodapp.Model.SearchAPIResponse;

public class SearchFragment extends Fragment {
    RequestManager manager;
    SearchRecipeAdapter searchRecipesAdapter;
    RecyclerView recyclerView;
    SearchView searchView;
    List<String> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private final SearchRecipeResponseListener searchRecipeResponseListener = new SearchRecipeResponseListener() {
        @Override
        public void didFetch(SearchAPIResponse response, String message) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            searchRecipesAdapter = new SearchRecipeAdapter(getContext(), response.results,recipeClickListener);
            recyclerView.setAdapter(searchRecipesAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        manager = new RequestManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_search);

        searchView = (SearchView) view.findViewById(R.id.sv_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                list.clear();
                list.add(query);
                manager.getSearchRecipe(searchRecipeResponseListener,list);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();
            Log.i("DETAIL",id);
            startActivity(new Intent(getContext(),DetailActivity.class)
                    .putExtra("id",id));
        }
    };

}