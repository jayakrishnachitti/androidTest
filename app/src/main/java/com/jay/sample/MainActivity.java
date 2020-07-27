package com.jay.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.jay.sample.databinding.ActivityMainBinding;

/***
 * Landing activity responsible for handling main list entries
 */
public class MainActivity extends AppCompatActivity {

    private RootViewModel rootViewModel;
    private MainAdapter myAdapter;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        SingletonViewModelFactory singletonNameViewModelFactory =
                new SingletonViewModelFactory(RootViewModel.getInstance(MainActivity.this));
        rootViewModel =
                ViewModelProviders.of(this, singletonNameViewModelFactory).get(RootViewModel.class);
        getListData();
    }

    /***
     * Fetching the data and refreshing the same for adapter
     */
    private void getListData() {
        rootViewModel.getDataResults().observe(this, imageResults -> {
            myAdapter = new MainAdapter(imageResults);
            activityMainBinding.rvM.setAdapter(myAdapter);
            myAdapter.setExpanded(true);
        });
    }
}
