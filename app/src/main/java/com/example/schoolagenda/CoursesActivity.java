package com.example.schoolagenda;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class CoursesActivity extends AppCompatActivity {
    private ListView Lista;
    private ArrayList<Course> courses;
    private CourseAdapter adapter;

    private DatabaseReference mRootReference;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        courses = new ArrayList<>();
        adapter = new CourseAdapter(this, R.layout.item_courses, courses);
        Lista = (ListView) findViewById(R.id.ListCourses);
        this.Lista.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        mRootReference = FirebaseDatabase.getInstance().getReference();

        String id = auth.getCurrentUser().getUid();
        mRootReference.child("Users").child(id).child("courses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String name = snapshot.child("name").getValue().toString();
                    String professor = snapshot.child("professor").getValue().toString();
                    ArrayList<String> days = new ArrayList<String>();
                    if((Boolean) snapshot.child("monday").getValue()){
                        days.add("Monday");
                    }
                    if((Boolean) snapshot.child("tuesday").getValue()){
                        days.add("Tuesday");
                    }
                    if((Boolean) snapshot.child("wednesday").getValue()){
                        days.add("Wednesday");
                    }
                    if((Boolean) snapshot.child("thursday").getValue()){
                        days.add("Thursday");
                    }
                    if((Boolean) snapshot.child("friday").getValue()){
                        days.add("Friday");
                    }
                    if((Boolean) snapshot.child("saturday").getValue()){
                        days.add("Saturday");
                    }
                    if((Boolean) snapshot.child("sunday").getValue()){
                        days.add("Sunday");
                    }
                    String sh = snapshot.child("start_hour").getValue().toString();
                    String sm = snapshot.child("start_minute").getValue().toString();
                    String eh = snapshot.child("end_hour").getValue().toString();
                    String em = snapshot.child("end_minute").getValue().toString();

                    String email = snapshot.child("email").getValue().toString();

                    addCourse(name,professor, Arrays.copyOf(days.toArray(),days.size(),String[].class),sh,sm,eh,em,email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void addCourse(String name, String professor, String[] days, String sh, String sm, String eh, String em, String email){
        Course c = new Course(name, professor, new Classes((String[]) days,sh,eh,sm,em), email);
        this.courses.add(c);
        this.Lista.setAdapter(adapter);
        this.adapter.notifyDataSetChanged();
    }
}
