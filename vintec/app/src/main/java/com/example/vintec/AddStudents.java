package com.example.vintec;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudents extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vintec Computer Education");
        getSupportActionBar().setSubtitle("Add New Certificate");
        session = new SessionManager(AddStudents.this.getApplicationContext());
    }
}
