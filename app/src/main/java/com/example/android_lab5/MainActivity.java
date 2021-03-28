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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String CUISINE_ID = "CUISINE_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list_view);

        AppViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(AppViewModel.class);
        viewModel.getRepo().getAllCuisines();
        viewModel.getRepo().getCuisines().observe(this, new Observer<List<Cuisine>>() {
            @Override
            public void onChanged(List<Cuisine> cuisines) {
                ArrayAdapter<Cuisine> arrayAdapter = new ArrayAdapter<Cuisine>(MainActivity.this, android.R.layout.simple_list_item_1);
                arrayAdapter.addAll(cuisines);
                listView.setAdapter(arrayAdapter);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cuisine cuisine =((Cuisine)parent.getItemAtPosition(position));
                Log.d(TAG, "onItemClick: "+ cuisine.name);
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                intent.putExtra(CUISINE_ID, cuisine.id);
                startActivity(intent);
            }
        });
    }
}