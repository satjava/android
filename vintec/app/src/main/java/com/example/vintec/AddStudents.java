package com.example.vintec;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddStudents extends AppCompatActivity {
    SessionManager session;
    DatePickerDialog picker;
    EditText issue_date,certi_no,name,course,duration,grade;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vintec Computer Education");
       getSupportActionBar().setSubtitle("Add New Certificate");
        session = new SessionManager(AddStudents.this.getApplicationContext());
        certi_no=(EditText)findViewById(R.id.certi_no);
        name=(EditText)findViewById(R.id.name);
        course=(EditText)findViewById(R.id.course);
        duration=(EditText)findViewById(R.id.duration);
        grade=(EditText)findViewById(R.id.grade);
        submit = (Button)findViewById(R.id.btnRegister);
        issue_date=(EditText)findViewById(R.id.issue_dt);
        issue_date.setOnClickListener(new View.OnClickListener() {
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
                            issue_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, year, month, day);
            picker.show();
        }
    });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //sname = name.getText().toString();
                if(!name.getText().toString().isEmpty() || !certi_no.getText().toString().isEmpty() || !course.getText().toString().isEmpty() || !duration.getText().toString().isEmpty() || !grade.getText().toString().isEmpty() || !issue_date.getText().toString().isEmpty()){
                    submitstudent();
                }
                else{
                    Toast.makeText(AddStudents.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void submitstudent(){
        Map<String, String> params = new HashMap<String, String>();

        params.put("name", name.getText().toString());
        params.put("certificate", certi_no.getText().toString());
        params.put("course", course.getText().toString());
        params.put("duration", duration.getText().toString());
        params.put("course", course.getText().toString());
        params.put("grade", grade.getText().toString());
        params.put("issue_date", issue_date.getText().toString());


        final ProgressDialog loading = ProgressDialog.show(AddStudents.this, "Vintec", "Updating Agency Supply", false, false);

        String URL = NetworkOperation.generateUrl("https://vintec.co.in/welcome/add_student", params);
        Log.d("URL->", URL);

        com.android.volley.toolbox.StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();

                        try {
                            JSONObject ja = new JSONObject(response);
                            String success = ja.getString("success");
                            if (success.equals("true")) {
                                Toast.makeText(AddStudents.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(AddStudents.this,StudentList.class);
                                startActivity(i);

                            } else {
                                Toast.makeText(AddStudents.this, "Failed Submission", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                    }
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(AddStudents.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }


}
