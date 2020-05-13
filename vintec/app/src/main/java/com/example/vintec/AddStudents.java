package com.example.vintec;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddStudents extends AppCompatActivity {
    SessionManager session;
    DatePickerDialog picker;
    EditText eText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vintec Computer Education");
       getSupportActionBar().setSubtitle("Add New Certificate");
        session = new SessionManager(AddStudents.this.getApplicationContext());
        eText=(EditText)findViewById(R.id.issue_dt);
        eText.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(AddStudents.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, year, month, day);
            picker.show();
        }
    });


    }
}
