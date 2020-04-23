package com.example.vintec;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StudentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vintec Computer Education");
        getSupportActionBar().setSubtitle("Students List");

    }
}
