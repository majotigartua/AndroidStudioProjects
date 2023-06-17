package com.example.scores.pojo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Integer> localScore = new MutableLiveData<Integer>();
    private final MutableLiveData<Integer> visitorScore = new MutableLiveData<Integer>();

    public MainViewModel() {
        resetScores();
    }

    public void resetScores() {
        localScore.setValue(0);
        visitorScore.setValue(0);
    }

    public MutableLiveData<Integer> getLocalScore() {
        return localScore;
    }

    public MutableLiveData<Integer> getVisitorScore() {
        return visitorScore;
    }

    public void addPointsToScore(int points, boolean isLocal) {
        if (isLocal) {
            localScore.setValue(localScore.getValue() + points);
        } else {
            visitorScore.setValue(visitorScore.getValue() + points);
        }
    }

    public void decreaseLocalScore() {
        if (localScore.getValue() > 0) {
            localScore.setValue(localScore.getValue() - 1);
        }
    }

    public void decreaseVisitorScore() {
        if (visitorScore.getValue() > 0) {
            visitorScore.setValue(visitorScore.getValue() - 1);
        }
    }

}