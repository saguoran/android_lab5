package com.example.android_lab5;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDao {

    @Query("SELECT * FROM cuisine")
    List<Cuisine> getAll();
    @Query("SELECT * FROM restaurant where cuisineId = :cuisineId")
    List<Restaurant> getRestaurantsByCuisineId(int cuisineId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Cuisine... cuisines);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Restaurant... restaurants);

}

