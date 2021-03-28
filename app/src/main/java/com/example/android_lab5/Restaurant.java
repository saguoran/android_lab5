package com.example.android_lab5;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant",foreignKeys = {@ForeignKey(entity = Cuisine.class,
        parentColumns = "id",
        childColumns = "cuisineId",
        onDelete = ForeignKey.CASCADE)
} )
public class Restaurant {
    @PrimaryKey
    @NonNull
    String name;
    @ColumnInfo(index = true)
    int cuisineId;
    String address;

    public Restaurant(@NonNull String name, int cuisineId, String address){
        this.name = name;
        this.cuisineId = cuisineId;
        this.address =address;
    }

    @Override
    public String toString() {
        return  name +"\nAt "+ address;
    }
}
