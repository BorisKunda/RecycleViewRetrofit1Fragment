package com.example.jbt.recycler;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_list, container, false);
        final RecyclerView recipeRV= (RecyclerView) v.findViewById(R.id.recipeRV);


        //create retrofit and assign BaseURL (will not change)
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.recipepuppy.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //connect the retrofit class with the interface calss
        //generate new instance of the interface and call it service
        RecipeService service = retrofit.create(RecipeService.class);

        Call<JsonReCipeResponse> allRecResponse =  service.getAllRecipesInJsonRes("egg");

        allRecResponse.enqueue(new Callback<JsonReCipeResponse>() {
            @Override
            public void onResponse(Response<JsonReCipeResponse> response, Retrofit retrofit) {
                JsonReCipeResponse resultWithInnerObjects=  response.body();
                ArrayList<Recipe> allRecipies= resultWithInnerObjects.results;

                //Looks like listview puts the elements one on top of the other (vertical linear layout)
                //recipeRV.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));

                //Looks like listview puts the elements one aside   of the other (horizontal linear layout)
                // recipeRV.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));


                recipeRV.setLayoutManager(new GridLayoutManager(getActivity(), 3));

                MyRecyclerAdapter adapter= new MyRecyclerAdapter(allRecipies, getActivity());

                recipeRV.setAdapter(adapter);


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });










        return v;
    }

}
