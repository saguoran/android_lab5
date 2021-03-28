package com.example.android_lab5;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import java.io.Serializable;

@Entity(tableName = "restaurant",foreignKeys = {@ForeignKey(entity = Cuisine.class,
        parentColumns = "id",
        childColumns = "cuisineId",
        onDelete = ForeignKey.CASCADE)
} )

@SuppressWarnings("serial")
public class Restaurant implements Serializable {
    @PrimaryKey
    @NonNull
    String name;
    @ColumnInfo(index = true)
    int cuisineId;
    String address;
    double latitude;
    double longitude;

    public Restaurant(@NonNull String name, int cuisineId, String address, double latitude, double longitude){
        this.name = name;
        this.cuisineId = cuisineId;
        this.address =address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return  name +"\nAt "+ address;
    }
}
