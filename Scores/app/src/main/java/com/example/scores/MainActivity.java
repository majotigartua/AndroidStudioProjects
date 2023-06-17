package com.example.scores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.scores.databinding.ActivityMainBinding;
import com.example.scores.pojo.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getLocalScore().observe(this, localScore -> {
            binding.localScoreTextView.setText(String.valueOf(localScore));
        });
        viewModel.getVisitorScore().observe(this, visitorScore -> {
            binding.visitorScoreTextView.setText(String.valueOf(visitorScore));
        });
        setUpButtons();
    }

    private void setUpButtons() {
        binding.localMinusButton.setOnClickListener(v -> {
            viewModel.decreaseLocalScore();
        });
        binding.localPlusButton.setOnClickListener(v -> {
            addPointsToScore(1, true);
        });
        binding.localTwoPointsButton.setOnClickListener(v -> {
            addPointsToScore(2, true);
        });
        binding.visitorMinusButton.setOnClickListener(v -> {
            viewModel.decreaseVisitorScore();
        });
        binding.visitorPlusButton.setOnClickListener(v -> {
            addPointsToScore(1, false);
        });
        binding.visitorTwoPointsButton.setOnClickListener(v -> {
            addPointsToScore(2, false);
        });
        binding.restartImageButton.setOnClickListener(v -> {
            resetScores();
        });
        binding.resultsImageButton.setOnClickListener(v -> {
            startScoreActivity();
        });
    }

    private void addPointsToScore(int points, boolean isLocal) {
        viewModel.addPointsToScore(points, isLocal);
    }

    private void resetScores() {
        viewModel.resetScores();
    }

    private void startScoreActivity() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra(ScoreActivity.LOCAL_SCORE, viewModel.getLocalScore().getValue());
        intent.putExtra(ScoreActivity.VISITOR_SCORE, viewModel.getVisitorScore().getValue());
        startActivity(intent);
    }

}