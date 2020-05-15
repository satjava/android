package com.example.vintec;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class StudentList extends AppCompatActivity implements StudentAdapter.GvScr3AdapterClickListener {
    ArrayList<StudenItems> list;
    SwipeRefreshLayout swipeRefreshLayout;
    SessionManager session;
    private Activity activity;
    private RecyclerView rv;
    StudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vintec Computer Education");
        getSupportActionBar().setSubtitle("Students List");
        session = new SessionManager(StudentList.this.getApplicationContext());
        list = new ArrayList<StudenItems>();
        adapter = new StudentAdapter(this,list);
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(adapter);
        loadSupply();

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadSupply();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });



    }

    public void loadSupply() {
        list.clear();
        //params.put("divn", spinDIVNval);
       Log.d("session user-->", session.getUserDetails().get(SessionManager.KEY_NAME));
//        Log.d("selected DAY by user-->", spinDAYval);
//        Log.d("selected EDI by user-->", spinEDIval);
//        Log.d("selected R by user-->", spinROUTEval);


        final ProgressDialog loading = ProgressDialog.show(StudentList.this, "Vintec", "Loading Students", false, false);

        com.android.volley.toolbox.StringRequest stringRequest = new StringRequest("https://vintec.co.in/welcome/all_student",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Log.d("agcd==>", response.toString());
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            boolean success=j.getBoolean("success");

                            if(success) {
                                JSONArray ja = j.getJSONArray("data");

                                //publ = new JSONArray(publString);

                                for (int i = 0; i < ja.length(); i++) {
                                    JSONObject object = ja.getJSONObject(i);

                                    StudenItems modules = new StudenItems();

                                    modules.setName(object.getString("stu_id"));
                                    modules.setCertificate(object.getString("certificate_no"));
                                    modules.setDuration(object.getString("course_duration"));
                                    modules.setCourse(object.getString("course_name"));
                                    modules.setIssuedate(object.getString("issu_date"));

                                    list.add(modules);


                                }


                            }
                            else
                            {

                                Toast.makeText(StudentList.this,"No Student Found!!",Toast.LENGTH_SHORT).show();
                            }
                            adapter = new StudentAdapter(StudentList.this,list);
                            adapter.setClickListener(StudentList.this);
                            rv.setAdapter(adapter);

                        } catch (JSONException e) {
                            loading.dismiss();
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
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
        RequestQueue requestQueue = Volley.newRequestQueue( StudentList.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);

    }

    @Override
    public void itemClicked(View view, int position, String file_name) {
        Toast.makeText(StudentList.this,file_name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemDelete(View view, String file_name) {
        Toast.makeText(StudentList.this,file_name,Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void itemClicked(View view, int position, String file_name) {
//        //view_file(file_name);
//        Toast.makeText(this,file_name,Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void itemDelete(View view, String file_name) {
//       // deleteGvFile(file_name);
//        Toast.makeText(this,file_name,Toast.LENGTH_SHORT).show();
//    }


}
