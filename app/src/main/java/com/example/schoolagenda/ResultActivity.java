package com.example.schoolagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView msg, needed, remaining;
    private float objective, pointsLasting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.msg = findViewById(R.id.msgTextView);
        this.needed = findViewById(R.id.needTextView);
        this.remaining = findViewById(R.id.remainingTextView);

        pointsLasting = 100 - getIntent().getFloatExtra("sumValue", 100);
        objective = 70 - getIntent().getFloatExtra("sumPoints", 70);
        float objectivePercent = (objective/pointsLasting*100);

        if (objective<= 0){
            this.msg.setText("You did it!!!");
            objectivePercent = 0;
        }
        else if (objective <= pointsLasting){
            this.msg.setText("It's Possible!!");
        } else {
            this.msg.setText("Better Luck Next Time :(");
        }


        this.needed.setText(objectivePercent + "%");

        this.remaining.setText(pointsLasting + "%");





    }
}
