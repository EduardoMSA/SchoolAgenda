package com.example.schoolagenda;

public class Grade {
    private float percent;
    private float grade;
    private String info;

    public Grade(String info, float percent, float grade) {
        this.percent = percent;
        this.grade = grade;
        this.info = info;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
