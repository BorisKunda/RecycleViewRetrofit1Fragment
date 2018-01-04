package com.example.jbt.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements MyClickRecipe {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.MainCL, new ListFragment()).commit();

        if(isLandscape())
        {
            DetailsFragment detailsFragment= new DetailsFragment();
            detailsFragment.recURL="http://google.com";
            getFragmentManager().beginTransaction().add(R.id.detailsLL,detailsFragment ).commit();
        }


    }

    private boolean isLandscape() {
        LinearLayout ll= (LinearLayout) findViewById(R.id.detailsLL);

        if(ll != null)
            return true;
        else
            return false;
    }

    @Override
    public void loadRecipe(Recipe e) {

        DetailsFragment detailsFragment= new DetailsFragment();
        detailsFragment.recURL=e.href;
        getFragmentManager().beginTransaction().replace(R.id.detailsLL,detailsFragment ).commit();


    }
}
