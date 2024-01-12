package id.ac.binus.foodapp.Listener;

import id.ac.binus.foodapp.Model.HomeRecipeAPIResponse;

public interface HomeRecipeResponseListener {
    void didFetch(HomeRecipeAPIResponse response, String message);
    void didError(String message);
}
