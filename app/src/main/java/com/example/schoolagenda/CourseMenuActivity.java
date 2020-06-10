package com.example.schoolagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CourseMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_menu);
    }

    public void addCourses(View view){
        startActivity(new Intent(this, AddCourse.class));
    }

    public void listCourses(View view){
        startActivity(new Intent(this, CoursesActivity.class));
    }
}
