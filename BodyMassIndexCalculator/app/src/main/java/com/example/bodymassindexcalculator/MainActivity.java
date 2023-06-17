package com.example.bodymassindexcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bodymassindexcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;

    private double height;
    private double weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.calculateBodyMassIndexButton.setOnClickListener(v -> {
            String height = binding.heightEditText.getText().toString();
            String weight = binding.weightEditText.getText().toString();
            if (!height.isEmpty() && !weight.isEmpty()) {
                try {
                    this.height = Double.parseDouble(height);
                    this.weight = Double.parseDouble(weight);
                    startBodyMassIndexActivity();
                } catch (NumberFormatException exception) {
                    Log.e("MainActivity", exception.getMessage());
                    Toast.makeText(this, R.string.invalid_information_label, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.empty_fields_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startBodyMassIndexActivity() {
        double bodyMassIndex = calculateBodyMassIndex();
        Intent intent = new Intent(this, BodyMassIndexActivity.class);
        intent.putExtra(BodyMassIndexActivity.HEIGHT, height);
        intent.putExtra(BodyMassIndexActivity.WEIGHT, weight);
        intent.putExtra(BodyMassIndexActivity.BODY_MASS_INDEX, bodyMassIndex);
        startActivity(intent);
    }

    private double calculateBodyMassIndex() {
        double heightInMeters = height / 100;
        return weight / Math.pow(heightInMeters, 2);
    }

}