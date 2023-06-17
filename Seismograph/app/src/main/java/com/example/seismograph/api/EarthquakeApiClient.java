package com.example.seismograph.api;

import com.example.seismograph.Earthquake;
import com.example.seismograph.api.model.Response;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class EarthquakeApiClient {

    private static final EarthquakeApiClient apiClient = new EarthquakeApiClient();
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private EarthquakeService earthquakeService;

    public static EarthquakeApiClient getInstance() {
        return apiClient;
    }

    public EarthquakeService getService() {
        if (earthquakeService == null) {
            earthquakeService = retrofit.create(EarthquakeService.class);
        }
        return earthquakeService;
    }

    public interface EarthquakeService {

        @GET("all_hour.geojson")
        Call<Response> getEarthquakes();

        @POST("earthquakes")
        Call<Response> post(@Body Earthquake earthquake);

        @PUT("earthquakes/{id}")
        Call<Response> put(@Path("id") int id, @Body Earthquake earthquake);

    }

}