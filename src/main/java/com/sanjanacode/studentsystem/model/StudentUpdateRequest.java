package com.sanjanacode.studentsystem.model;

public class StudentUpdateRequest {
    private int satScore;
    private String name;

    public StudentUpdateRequest() {
    }

    public int getSatScore() {
        return satScore;
    }

    public void setSatScore(int satScore) {
        this.satScore = satScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
