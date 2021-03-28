package com.example.android_lab5;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "cuisine")
public class Cuisine {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;
    String name;


    public Cuisine(int id,String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
