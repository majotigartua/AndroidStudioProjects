package com.example.seismograph.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.seismograph.Earthquake;
import com.example.seismograph.api.RequestStatus;
import com.example.seismograph.api.StatusWithDescription;
import com.example.seismograph.database.EarthquakeDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Earthquake>> earthquakes = new MutableLiveData<>();
    private final MainRepository repository;
    private MutableLiveData<StatusWithDescription> status = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        EarthquakeDatabase database = EarthquakeDatabase.getDatabase(application);
        repository = new MainRepository(database);
    }

    public LiveData<List<Earthquake>> getEarthquakes() {
        return repository.getEarthquakes();
    }

    public MutableLiveData<StatusWithDescription> getStatus() {
        return status;
    }

    public void loadEarthquakes() {
        status.setValue(new StatusWithDescription(RequestStatus.LOADING, ""));
        repository.loadAndSaveEarthquakes(new MainRepository.DownloadStatusListener() {
            @Override
            public void downloadSuccess() {
                status.setValue(new StatusWithDescription(RequestStatus.DONE, ""));
            }

            @Override
            public void downloadError(String message) {
                status.setValue(new StatusWithDescription(RequestStatus.LOADING, message));
            }
        });
    }

}