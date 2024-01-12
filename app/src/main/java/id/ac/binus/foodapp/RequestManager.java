package id.ac.binus.foodapp;

import android.content.Context;

import java.util.List;

import id.ac.binus.foodapp.Listener.HomeRecipeResponseListener;
import id.ac.binus.foodapp.Listener.RecipeDetailResponseListener;
import id.ac.binus.foodapp.Listener.SearchRecipeResponseListener;
import id.ac.binus.foodapp.Model.HomeRecipeAPIResponse;
import id.ac.binus.foodapp.Model.RecipeDetailAPIResponse;
import id.ac.binus.foodapp.Model.SearchAPIResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
//  GET https://api.spoonacular.com/recipes/random
    public RequestManager(Context context){
        this.context = context;
    }

    public void getHomeRecipe(HomeRecipeResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<HomeRecipeAPIResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "10", tags);
        call.enqueue(new Callback<HomeRecipeAPIResponse>() {
            @Override
            public void onResponse(Call<HomeRecipeAPIResponse> call, Response<HomeRecipeAPIResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<HomeRecipeAPIResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecommendRecipe(HomeRecipeResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<HomeRecipeAPIResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "1", tags);
        call.enqueue(new Callback<HomeRecipeAPIResponse>() {
            @Override
            public void onResponse(Call<HomeRecipeAPIResponse> call, Response<HomeRecipeAPIResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<HomeRecipeAPIResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<HomeRecipeAPIResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
                );
    }

    public void getSearchRecipe(SearchRecipeResponseListener listener, List<String> query){
        CallSearchRecipes callSearchRecipes = retrofit.create(CallSearchRecipes.class);
        Call<SearchAPIResponse> call = callSearchRecipes.callSearchRecipe(context.getString(R.string.api_key), "10", query);
        call.enqueue(new Callback<SearchAPIResponse>() {
            @Override
            public void onResponse(Call<SearchAPIResponse> call, Response<SearchAPIResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<SearchAPIResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
    private interface CallSearchRecipes{
        @GET("recipes/complexSearch")
        Call<SearchAPIResponse> callSearchRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("query") List<String> query
        );
    }

    public void getRecipeDetails(RecipeDetailResponseListener listener, int id){
        CallDetailRecipes callDetailRecipes = retrofit.create(CallDetailRecipes.class);
        Call<RecipeDetailAPIResponse> call = callDetailRecipes.callDetailRecipe(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailAPIResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailAPIResponse> call, Response<RecipeDetailAPIResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailAPIResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallDetailRecipes{
        @GET("recipes/{id}/information")
        Call<RecipeDetailAPIResponse> callDetailRecipe(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
