package com.ralphdosser.skorboye.models;

public class PlayerScore {

    private String name;

    private int Score;

    public PlayerScore(String name, int score) {
        this.name = name;
        Score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
