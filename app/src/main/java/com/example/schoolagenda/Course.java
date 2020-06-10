package com.example.schoolagenda;

public class Course {
    private String name, prof, email;
    private Classes classSchedule;

    public Course(String name, String prof, Classes classSchedule, String email) {
        this.name = name;
        this.prof = prof;
        this.email = email;
        this.classSchedule = classSchedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public Classes getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(Classes classSchedule) {
        this.classSchedule = classSchedule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
