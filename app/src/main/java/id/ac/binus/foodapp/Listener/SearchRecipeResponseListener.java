package id.ac.binus.foodapp.Listener;

import id.ac.binus.foodapp.Model.HomeRecipeAPIResponse;
import id.ac.binus.foodapp.Model.SearchAPIResponse;

public interface SearchRecipeResponseListener {
    void didFetch(SearchAPIResponse response, String message);
    void didError(String message);
}
