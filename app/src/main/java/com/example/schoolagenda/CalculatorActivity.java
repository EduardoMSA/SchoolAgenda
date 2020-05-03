package com.example.schoolagenda;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorActivity";
    private EditText info, grade, value;
    private ArrayList<Grade> grades;
    private ListView Lista;
    private GradeListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Log.d(TAG, "on Create: Started");
        Lista = (ListView) findViewById(R.id.Lista);

        grades = new ArrayList<>();

        info = (EditText) findViewById(R.id.infoEditText);
        grade = (EditText) findViewById(R.id.gradeEditText);
        value = (EditText) findViewById(R.id.valueEditText);

        adapter = new GradeListAdapter(this, R.layout.item_calculator, grades);


    }

    public void addGrade(View view){
        Grade g = new Grade(this.info.getText().toString(),Float.parseFloat(this.value.getText().toString()),Float.parseFloat(this.grade.getText().toString()));
        this.grades.add(g);
        this.Lista.setAdapter(adapter);
        this.adapter.notifyDataSetChanged();
    }

    public void clearAll(View view){
        this.grades.clear();
        this.Lista.setAdapter(adapter);
        this.adapter.notifyDataSetChanged();
    }

    public void calculate(View view){

        float sumValue = 0;
        float sumPoints = 0;

        for(int i = 0; i<this.grades.size(); i++){
            Grade g = this.grades.get(i);
            sumValue += g.getPercent();
            sumPoints += (g.getPercent()*g.getGrade()/100);
        }

        Intent result = new Intent(getApplicationContext(),ResultActivity.class);
        result.putExtra("sumValue",sumValue);
        result.putExtra("sumPoints",sumPoints);
        startActivity(result);

    }

}
