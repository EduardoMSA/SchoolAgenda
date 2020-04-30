package com.example.schoolagenda;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    // App elements
    private EditText editEmail, editPassword;
    private Button buttonLoginIn;
    private String email ="", password="";

    // Database Elements
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // App elements
        editEmail = (EditText)findViewById(R.id.emailEditText);
        editPassword = (EditText)findViewById(R.id.passwordEditText);

        buttonLoginIn = (Button)findViewById(R.id.loginInButton);

        // Firebase elements
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        buttonLoginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()){
                    if(password.length()>=6){
                        loginUser();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "El password debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(LoginActivity.this, "Debes completar los campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loginUser(){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesion, comprueba los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
