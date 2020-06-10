package com.example.schoolagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MailActivity extends AppCompatActivity {

    EditText cc, subject, text;
    TextView to, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        cc = findViewById(R.id.editTextCC);
        subject = findViewById(R.id.editTextSubject);
        text = findViewById(R.id.editTextText);
        to = findViewById(R.id.textViewTo);
        name = findViewById(R.id.textViewCourseName);

        to.setText("To: " + getIntent().getStringExtra("mail"));
        name.setText(getIntent().getStringExtra("name"));
    }

    public void sendMail(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        if(!cc.getText().toString().isEmpty()) {
            if (!isValidEmail(cc.getText().toString())) {
                Toast.makeText(this, "Enter a valid email for CC", Toast.LENGTH_LONG).show();
                return;
            }
            String[] CC = {cc.getText().toString()};
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
        }
        if(subject.getText().toString().isEmpty()){
            Toast.makeText(this,"Enter a subject", Toast.LENGTH_LONG).show();
            return;
        }
        if(text.getText().toString().isEmpty()){
            Toast.makeText(this,"Enter a message", Toast.LENGTH_LONG).show();
            return;
        }
        String[] TO = {getIntent().getStringExtra("mail")};
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, text.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            clear();
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }

    public void clear(){
        cc.setText("");
        subject.setText("");
        text.setText("");
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
