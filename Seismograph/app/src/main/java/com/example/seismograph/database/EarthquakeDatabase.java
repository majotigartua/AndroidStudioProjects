package com.example.seismograph.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.seismograph.Earthquake;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Earthquake.class}, version = 1)
public abstract class EarthquakeDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile EarthquakeDatabase INSTANCE;

    public static EarthquakeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EarthquakeDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EarthquakeDatabase.class, "earthquakes_database").build();
            }
        }
        return INSTANCE;
    }

    public abstract EarthquakeDAO earthquakeDAO();

}