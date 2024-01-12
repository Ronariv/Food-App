package id.ac.binus.foodapp.Listener;

import id.ac.binus.foodapp.Model.RecipeDetailAPIResponse;

public interface RecipeDetailResponseListener {
    void didFetch(RecipeDetailAPIResponse response, String message);
    void didError(String message);
}
