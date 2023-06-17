package com.example.scores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.scores.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {

    public static final String LOCAL_SCORE = "local_score";
    public static final String VISITOR_SCORE = "visitor_score";

    private ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int localScore = getIntent().getExtras().getInt(LOCAL_SCORE);
        int visitorScore = getIntent().getExtras().getInt(VISITOR_SCORE);
        if (localScore > visitorScore) {
            binding.winnerTextView.setText(getString(R.string.local_won_label));
        } else if (localScore < visitorScore) {
            binding.winnerTextView.setText(getString(R.string.visitor_won_label));
        } else {
            binding.winnerTextView.setText(R.string.tie_label);
        }
        binding.scoreTextView.setText(getString(R.string.local_vs_visitor_score_format, localScore, visitorScore));
    }

}