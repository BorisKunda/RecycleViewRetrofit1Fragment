package com.example.jbt.recycler;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jbt on 15/12/2017.
 */

public interface RecipeService {


    //http://www.recipepuppy.com/api/?i=egg
    //http://www.recipepuppy.com/-base URL will be given in MainActivity
    // api/- API link
    //i=egg - Query String  can be changed.

    //if the JSOn result is list like in https://api.github.com/users?since=180
    //the object return should be list   Call<List<User>> getAllUsers(@Query("since") String usersSinceID);
    //if the response is an object return one Object (the results are inside it)

    @GET("api")
    Call<JsonReCipeResponse> getAllRecipesInJsonRes(@Query("i") String ingredient);
}

