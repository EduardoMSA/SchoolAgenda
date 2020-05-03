package com.example.schoolagenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {

    private Button buttonSignOut;

    private TextView nameTextView, emailTextView;
    private GridLayout mainGridLayout;
    private DatabaseReference database;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        nameTextView = (TextView)findViewById(R.id.textViewName);
        emailTextView = (TextView)findViewById(R.id.textViewEmail);
        mainGridLayout = (GridLayout)findViewById(R.id.gridLayout);
        buttonSignOut = (Button)findViewById(R.id.buttonSignOut);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();


        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(MenuActivity.this, RegisterActivity.class));
                finish();
            }
        });
        setSingleEvent(mainGridLayout);
        getUserInfo();
    }

    private void setSingleEvent(GridLayout gl){
        for(int i=0; i<gl.getChildCount(); i++){

            CardView cardView = (CardView) gl.getChildAt(i);
            final int current = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // startActivity(new Intent(MenuActivity.this, AgendaActivity.class));

                    if(current == 0){
                        startActivity(new Intent(MenuActivity.this, AgendaActivity.class));
                    }
                    else if (current == 1){
                        startActivity(new Intent(MenuActivity.this, CalculatorActivity.class));
                    }
                    else if (current == 2){
                        startActivity(new Intent(MenuActivity.this, NotesActivity.class));
                    }
                    else if (current == 3){
                        startActivity(new Intent(MenuActivity.this, CoursesActivity.class));
                    }
                    else{
                        Toast.makeText(MenuActivity.this, "Set an activity for this item!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    private void getUserInfo(){
        String id = auth.getCurrentUser().getUid();
        database.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();

                    String str = "Welcome, "+ name;
                    nameTextView.setText(str);
                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
