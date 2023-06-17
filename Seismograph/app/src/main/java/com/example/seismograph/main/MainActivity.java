package com.example.seismograph.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.seismograph.api.RequestStatus;
import com.example.seismograph.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.earthquakeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EarthquakeAdapter adapter = new EarthquakeAdapter(this);
        adapter.setOnItemClickListener(earthquake -> Toast.makeText(this, earthquake.getPlace(), Toast.LENGTH_SHORT).show());
        binding.earthquakeRecyclerView.setAdapter(adapter);
        MainViewModel viewModel = new ViewModelProvider(this, new MainViewModelFactory(getApplication())).get(MainViewModel.class);
        viewModel.loadEarthquakes();
        viewModel.getEarthquakes().observe(this, adapter::submitList);
        viewModel.getStatus().observe(this, status -> {
            if (status.getStatus() == RequestStatus.LOADING) {
                binding.loadingWheel.setVisibility(View.VISIBLE);
            } else {
                binding.loadingWheel.setVisibility(View.GONE);
            }
            if (status.getStatus() == RequestStatus.ERROR) {
                Toast.makeText(this, status.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}