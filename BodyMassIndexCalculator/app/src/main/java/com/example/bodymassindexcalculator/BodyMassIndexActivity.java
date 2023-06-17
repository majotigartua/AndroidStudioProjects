package com.example.bodymassindexcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bodymassindexcalculator.databinding.ActivityBodyMassIndexBinding;

import java.text.DecimalFormat;

public class BodyMassIndexActivity extends AppCompatActivity {

    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String BODY_MASS_INDEX = "body_mass_index";

    ActivityBodyMassIndexBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBodyMassIndexBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        binding.setHeight(String.valueOf(bundle.getDouble(HEIGHT)));
        binding.setWeight(String.valueOf(bundle.getDouble(WEIGHT)));
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        double bodyMassIndex = bundle.getDouble(BODY_MASS_INDEX);
        binding.setBodyMassIndex(decimalFormat.format(bodyMassIndex));
        paintRow(bodyMassIndex);
    }

    private void paintRow(Double bodyMassIndex) {
        if (bodyMassIndex < 18.5) {
            binding.underweightRow.setBackgroundColor(getColor(R.color.blue));
        } else if (bodyMassIndex >= 18.5  && bodyMassIndex < 25) {
            binding.normalWeightRow.setBackgroundColor(getColor(R.color.green));
        } else if (bodyMassIndex >= 25 && bodyMassIndex < 30) {
            binding.overweightRow.setBackgroundColor(getColor(R.color.yellow));
        } else if (bodyMassIndex >= 30 && bodyMassIndex < 35) {
            binding.typeOneObesity.setBackgroundColor(getColor(R.color.orange));
        } else if (bodyMassIndex >= 35 && bodyMassIndex < 40) {
            binding.typeTwoObesity.setBackgroundColor(getColor(R.color.red));
        } else {
            binding.typeThreeObesity.setBackgroundColor(getColor(R.color.pink));
        }
    }

}