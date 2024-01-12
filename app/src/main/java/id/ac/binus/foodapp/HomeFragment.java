package id.ac.binus.foodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.binus.foodapp.Adapter.HomeRecipeAdapter;
import id.ac.binus.foodapp.Listener.HomeRecipeResponseListener;
import id.ac.binus.foodapp.Listener.RecipeClickListener;
import id.ac.binus.foodapp.Model.HomeRecipeAPIResponse;

public class HomeFragment extends Fragment {

    RequestManager manager;
    HomeRecipeAdapter homeRecipesAdapter;
    RecyclerView recyclerView;
    List<String> tags = new ArrayList<>();
    Spinner spinner;
    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private final HomeRecipeResponseListener homeRecipeResponseListener = new HomeRecipeResponseListener() {
        @Override
        public void didFetch(HomeRecipeAPIResponse response, String message) {

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            homeRecipesAdapter = new HomeRecipeAdapter(getContext(),response.recipes,recipeClickListener);
            recyclerView.setAdapter(homeRecipesAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        manager = new RequestManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_home);
        spinner = (Spinner) view.findViewById(R.id.spinner_types);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.meal_types,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

//        manager.getHomeRecipe(homeRecipeResponseListener,tags);
        return view;


    }

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            manager.getHomeRecipe(homeRecipeResponseListener,tags);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();
            Log.i("DETAIL",id);
            startActivity(new Intent(getContext(),DetailActivity.class)
                    .putExtra("id",id));
        }
    };
//    @Override
//    public void onResume() {
//        super.onResume();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
//
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
//    }

}