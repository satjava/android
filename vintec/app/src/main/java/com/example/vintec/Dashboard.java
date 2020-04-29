package com.example.vintec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    SessionManager session;
    private Button logoutbtn,studentlist;
    private ImageButton list,addstudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Vintec Computer Education");

        session = new SessionManager(Dashboard.this.getApplicationContext());
        logoutbtn = (Button)findViewById(R.id.logout);
        //studentlist = (Button)findViewById(R.id.studentlist);
        list = (ImageButton) findViewById(R.id.listimg);
        addstudent = (ImageButton) findViewById(R.id.add);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.logoutUser();
                finish();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard.this,StudentList.class);
                startActivity(i);
            }
        });
        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard.this,AddStudents.class);
                startActivity(i);
            }
        });
    }
}
