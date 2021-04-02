package com.example.android_lab5;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String RESTAURANT ="RESTAURANT";
    private static Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        restaurant = (Restaurant) intent.getSerializableExtra(RESTAURANT);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker to the restaurant and move the camera
        LatLng latitudeLongitude = new LatLng(restaurant.latitude, restaurant.longitude);

        Marker marker = mMap.addMarker(new MarkerOptions().position(latitudeLongitude).title(restaurant.name));

        // zoom in the map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeLongitude, 15f));

        // display title info
        marker.showInfoWindow();

        // set default map type to normal
        mMap.setMapType(mMap.MAP_TYPE_NORMAL);
     }

    // switch map type
    public void ChangeMapType (View view){

        if(mMap.getMapType() == mMap.MAP_TYPE_NORMAL)
            mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
        else
            mMap.setMapType(mMap.MAP_TYPE_NORMAL);
    }
}