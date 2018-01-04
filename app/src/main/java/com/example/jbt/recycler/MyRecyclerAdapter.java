package com.example.jbt.recycler;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


//1.create a class that extends RecyclerView.Adapter
//4. put inside the < > your class
//5. implement abstract methods
public class MyRecyclerAdapter extends RecyclerView.Adapter< MyRecyclerAdapter.MyHolder> {

    ArrayList<Recipe> allRecs;
    Activity context;


    public MyRecyclerAdapter(ArrayList<Recipe> allRecs , Activity context) {
        this.allRecs = allRecs;
        this.context= context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.single_rec ,null);
        MyHolder singleVH= new MyHolder(v);
        return singleVH;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Recipe current =  allRecs.get(position);
        holder.bindDataFromArrayToView(current);

    }

    @Override
    public int getItemCount() {
        return allRecs.size();
    }

    //2. create inner Class that extends RecyclerView.ViewHolder
    //3. override default constructor

    //INNER CLASS EXTENDS RecyclerView.ViewHolder
    class MyHolder extends RecyclerView.ViewHolder
    {
        View myView;

        public MyHolder(View itemView) {
            super(itemView);
            myView=itemView;
        }
        public  void bindDataFromArrayToView(final Recipe currentRec)
        {
            TextView tv= (TextView) myView.findViewById(R.id.kotertTV);
            tv.setText(currentRec.title);
            ImageView imageView= (ImageView) myView.findViewById(R.id.recIV);
            Picasso.with(context).load(currentRec.thumbnail).into(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyClickRecipe myClickRecipe= (MyClickRecipe) context;
                    myClickRecipe.loadRecipe(currentRec);
                }
            });


        }

    }


}
