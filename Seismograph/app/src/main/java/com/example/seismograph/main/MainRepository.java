package com.example.seismograph.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.seismograph.Earthquake;
import com.example.seismograph.api.EarthquakeApiClient;
import com.example.seismograph.api.model.Feature;
import com.example.seismograph.database.EarthquakeDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private final EarthquakeDatabase database;
    EarthquakeApiClient.EarthquakeService service = EarthquakeApiClient.getInstance().getService();

    public MainRepository(EarthquakeDatabase database) {
        this.database = database;
    }

    public LiveData<List<Earthquake>> getEarthquakes() {
        return database.earthquakeDAO().getEarthquakes();
    }

    public void loadAndSaveEarthquakes(DownloadStatusListener downloadStatusListener) {
        service.getEarthquakes().enqueue(new Callback<com.example.seismograph.api.model.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.example.seismograph.api.model.Response> call, @NonNull Response<com.example.seismograph.api.model.Response> response) {
                List<Earthquake> earthquakes = getEarthquakesWithMoshi(response.body());
                EarthquakeDatabase.databaseWriteExecutor.execute(() -> database.earthquakeDAO().insertAll(earthquakes));
                downloadStatusListener.downloadSuccess();
            }

            @Override
            public void onFailure(@NonNull Call<com.example.seismograph.api.model.Response> call, @NonNull Throwable t) {
                downloadStatusListener.downloadError(t.getMessage());
            }
        });
    }

    private List<Earthquake> getEarthquakesWithMoshi(com.example.seismograph.api.model.Response response) {
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        List<Feature> features = response.getFeatures();
        for (Feature feature : features) {
            String id = feature.getId();
            double magnitude = feature.getProperties().getMagnitude();
            String place = feature.getProperties().getPlace();
            long time = feature.getProperties().getTime();
            double longitude = feature.getGeometry().getCoordinates().get(0);
            double latitude = feature.getGeometry().getCoordinates().get(1);
            Earthquake earthquake = new Earthquake(id, magnitude, place, time, longitude, latitude);
            earthquakes.add(earthquake);
        }
        return earthquakes;
    }

    public void post(Earthquake earthquake, Callback<com.example.seismograph.api.model.Response> callback) {
        Call<com.example.seismograph.api.model.Response> call = service.post(earthquake);
        call.enqueue(callback);
    }

    public void put(int id, Earthquake earthquake, Callback<com.example.seismograph.api.model.Response> callback) {
        Call<com.example.seismograph.api.model.Response> call = service.post(earthquake);
        call.enqueue(callback);
    }

    public interface DownloadStatusListener {

        void downloadSuccess();

        void downloadError(String message);

    }

}