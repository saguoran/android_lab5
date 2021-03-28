package com.example.android_lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {
    private static final String TAG = "RestaurantsActivity";
    private static final String RESTAURANT_ADDRESS = "RESTAURANT_ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ListView listView = findViewById(R.id.list_view);
        Intent intent = getIntent();
        int cuisineId = intent.getIntExtra(MainActivity.CUISINE_ID, -1);
        AppViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(AppViewModel.class);
        viewModel.getRepo().getRestaurants(cuisineId);
        viewModel.getRepo().getRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                ArrayAdapter<Restaurant> arrayAdapter = new ArrayAdapter<Restaurant>(RestaurantsActivity.this, android.R.layout.simple_list_item_1);
                arrayAdapter.addAll(restaurants);
                listView.setAdapter(arrayAdapter);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant =((Restaurant)parent.getItemAtPosition(position));
                Log.d(TAG, "onItemClick: "+ restaurant);
//                Intent intent = new Intent(*.this, *.class);
//                intent.putExtra(RESTAURANT_ADDRESS, restaurant.address);
//                startActivity(intent);
            }
        });
    }
}