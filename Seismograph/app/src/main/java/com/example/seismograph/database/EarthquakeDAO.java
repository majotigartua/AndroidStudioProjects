package com.example.seismograph.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.seismograph.Earthquake;

import java.util.List;

@Dao
public interface EarthquakeDAO {

    @Delete
    void deleteEarthquake(Earthquake earthquake);

    @Query("SELECT * FROM earthquakes")
    LiveData<List<Earthquake>> getEarthquakes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Earthquake> earthquakes);

    @Update
    void updateEarthquake(Earthquake earthquake);

}