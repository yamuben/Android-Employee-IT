package com.example.employeeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewEmployee extends AppCompatActivity {


    TextView edfullname, edemail,eddepartment,eddistrict,edpassword;
    Button logoutBtn;

    FirebaseUser user;
    DatabaseReference reference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        edfullname= findViewById(R.id.fullnameidb);
        edemail = findViewById(R.id.emailidb);
        edpassword = findViewById(R.id.passwordidb);
        eddepartment = findViewById(R.id.departmentidb);
        eddistrict = findViewById(R.id.districtidb);

        logoutBtn = findViewById(R.id.logoutBtnb);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users userProfile = snapshot.getValue(users.class);
                if(userProfile != null){
                    String fullname,email,department,district,password;
                    fullname = userProfile.fullname;
                    email = userProfile.email;
                    department = userProfile.department;
                    district = userProfile.district;
                    password = userProfile.password;

                    edfullname.setText(fullname);
                    edemail.setText(email);
                    eddepartment.setText(department);
                    eddistrict.setText(district);
                    edpassword.setText(password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ViewEmployee.this,"Something went wrong", Toast.LENGTH_LONG).show();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewEmployee.this,MainActivity.class));
            }
        });

    }
}