package com.example.employeeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText username , password;

    Button  loginbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usernameid);
        password = findViewById(R.id.passwordid);

        loginbtn = findViewById(R.id.loginid);
        mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernam,pass;
                usernam= username.getText().toString().trim();
                pass = password.getText().toString().trim();

                if(usernam.isEmpty()){
                    username.setError("Please enter Valid email");
                    username.requestFocus();
                    return;
                }
                if(pass.isEmpty()){
                    password.setError("Please enter Valid email");
                    password.requestFocus();
                    return;
                }

                if(usernam.equals("admin@gmail.com") && pass.equals("12345678"))
                {
                    startActivity(new Intent(MainActivity.this, Registering.class));}
                else{
                    Toast.makeText(MainActivity.this,"Welcome Employee ", Toast.LENGTH_LONG).show();

                    mAuth.signInWithEmailAndPassword(usernam,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                startActivity(new Intent(MainActivity.this,ViewEmployee.class));

                            }else {
                                Toast.makeText(MainActivity.this,"Failed to Login! please check credential ", Toast.LENGTH_LONG).show();

                            }
                        }
                    });


                }
//                startActivity(new Intent(MainActivity.this, Registering.class));
            }
        });
    }



    private void openAdminDashboard() {


    }
}