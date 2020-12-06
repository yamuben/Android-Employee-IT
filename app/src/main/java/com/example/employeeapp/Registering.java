package com.example.employeeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Registering extends AppCompatActivity {

    EditText edfullname, edemail,eddepartment,eddistrict,edpassword;
    Button exitbtn,registerbtn,viewListBtn;
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registering);

        edfullname= findViewById(R.id.fullnameid);
        edemail = findViewById(R.id.emailid);
        edpassword = findViewById(R.id.passwordid);
        eddepartment = findViewById(R.id.departmentid);
        eddistrict = findViewById(R.id.districtid);


        mAuth = FirebaseAuth.getInstance();

        registerbtn = findViewById(R.id.registerBtn);


        viewListBtn = findViewById(R.id.viewlistBtn);
        exitbtn = findViewById(R.id.cancelBtn);


        viewListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registering.this,DisplayList.class));
            }
        });

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registering.this, MainActivity.class));
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname,email,department,district,password;

                fullname = edfullname.getText().toString().trim();
                email = edemail.getText().toString().trim();
                department = eddepartment.getText().toString().trim();
                district = eddistrict.getText().toString().trim();
                password = edpassword.getText().toString().trim();


        if(fullname.isEmpty()){
            edfullname.setError("Full names are required!");
            edfullname.requestFocus();
            return;
        }
        if(email.isEmpty()){
            edemail.setError("Email is required!");
            edemail.requestFocus();
            return;
        }
        if(department.isEmpty()){
            eddepartment.setError("Department is required!");
            eddepartment.requestFocus();
            return;
        }
        if(password.isEmpty()){
            edpassword.setError("password is required!");
            edpassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            edemail.setError("please provide valid emails!");
            edemail.requestFocus();
            return;
        }


              mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()){
                          users user = new users(fullname,email,department,district,password);
                          FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid())
                                  .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {

                                  if(task.isSuccessful()){
                                      Toast.makeText(Registering.this,"user has been registered succesfully",Toast.LENGTH_LONG).show();
                                  }else {
                                      Toast.makeText(Registering.this,"user has been registered failed",Toast.LENGTH_LONG).show();

                                  }
                              }
                          });
                      }else {
                          Toast.makeText(Registering.this,"user has been registered failed",Toast.LENGTH_LONG).show();

                      }
                  }
              });

            }
        });



    }


}