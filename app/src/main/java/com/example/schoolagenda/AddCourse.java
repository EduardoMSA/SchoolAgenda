package com.example.schoolagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddCourse extends AppCompatActivity {

    EditText name, prof, start, end, email;
    CheckBox mo, tu, we, th, fr, sa, su;
    private DatabaseReference mRootReference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        name = findViewById(R.id.editTextCourseName);
        prof = findViewById(R.id.editTextProfessor);
        start = findViewById(R.id.editTextStart);
        end = findViewById(R.id.editTextEnd);
        mo = findViewById(R.id.checkBoxMo);
        tu = findViewById(R.id.checkBoxTu);
        we = findViewById(R.id.checkBoxWe);
        th = findViewById(R.id.checkBoxTh);
        fr = findViewById(R.id.checkBoxFr);
        sa = findViewById(R.id.checkBoxSa);
        su = findViewById(R.id.checkBoxSu);
        email = findViewById(R.id.editTextEmail);

        auth = FirebaseAuth.getInstance();
        mRootReference = FirebaseDatabase.getInstance().getReference();

    }

    public void clear(){
        name.setText("");
        prof.setText("");
        start.setText("");
        end.setText("");
        email.setText("");
        mo.setChecked(false);
        tu.setChecked(false);
        we.setChecked(false);
        th.setChecked(false);
        fr.setChecked(false);
        sa.setChecked(false);
        su.setChecked(false);
    }

    public void addCourse(View view){

        if(name.getText().toString().isEmpty() || prof.getText().toString().isEmpty()){
            Toast.makeText(this,"Enter the info required", Toast.LENGTH_LONG).show();
            return;
        }

        if(!mo.isChecked()&&!tu.isChecked()&&!we.isChecked()&&!th.isChecked()&&!fr.isChecked()&&!sa.isChecked()&&!su.isChecked()){
            Toast.makeText(this,"Select days", Toast.LENGTH_LONG).show();
            return;
        }

        String id = auth.getCurrentUser().getUid();
        Map<String,Object> data = new HashMap<>();
        data.put("name",name.getText().toString());
        data.put("professor",prof.getText().toString());
        data.put("monday",mo.isChecked());
        data.put("tuesday",tu.isChecked());
        data.put("wednesday",we.isChecked());
        data.put("thursday",th.isChecked());
        data.put("friday",fr.isChecked());
        data.put("saturday",sa.isChecked());
        data.put("sunday",su.isChecked());

        try {

            String[] st = start.getText().toString().split(":",2);
            String[] et = end.getText().toString().split(":",2);
            int sh, sm, eh, em;
            sh = Integer.parseInt(st[0]);
            sm = Integer.parseInt(st[1]);
            eh = Integer.parseInt(et[0]);
            em = Integer.parseInt(et[1]);
            if(sh < 0 || sh > 23 || eh < 0 || eh > 23 || sm < 0 || sm > 59 || em < 0 || em > 59){
                Toast.makeText(this,"Insert a valid time (24h format)", Toast.LENGTH_LONG).show();
                return;
            }
            data.put("start_hour",st[0]);
            data.put("start_minute",st[1]);
            data.put("end_hour",et[0]);
            data.put("end_minute",et[1]);
        } catch (Exception e){
            Toast.makeText(this,"Insert a valid time (24h format)", Toast.LENGTH_LONG).show();
            return;
        }

        if(isValidEmail(email.getText().toString())){
            data.put("email",email.getText().toString());
        } else {
            Toast.makeText(this,"Enter a valid email for contact", Toast.LENGTH_LONG).show();
            return;
        }


        mRootReference.child("Users").child(id).child("courses").push().setValue(data);
        Toast.makeText(this,"Course Added", Toast.LENGTH_LONG).show();
        clear();

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
