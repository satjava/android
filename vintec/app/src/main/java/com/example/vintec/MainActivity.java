package com.example.vintec;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText usernameField,passwordField;
    private Button loginbtn;
    String user,pass;
    JSONArray MYJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameField = (EditText)findViewById(R.id.username);
        passwordField = (EditText)findViewById(R.id.password);
        loginbtn = (Button)findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Loging",Toast.LENGTH_SHORT).show();
                 loginFun();
            }
        });
    }
    public void loginFun(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("act", "SUPPLY_ALTR_DIV");
        params.put("empcd", "JK0066");
        user = usernameField.getText().toString();
        pass = passwordField.getText().toString();

        String URI = "https://vintec.co.in/welcome/admin_login/"+user+"/"+pass;
       // String URL = NetworkOperation.generateUrl(URI, params);

        Log.d("ADMIN LOGIN URL->", URI);
        StringRequest stringRequest = new StringRequest(URI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject j = null;

                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);
                            boolean success = j.getBoolean("success");
                            Log.d("ADMIN LOGIN STATUS->", String.valueOf(j));
                            if (success) {

                                Log.d("ADMIN LOGIN STATUS->", String.valueOf(success));
                                Intent i = new Intent(MainActivity.this,StudentList.class);
                                startActivity(i);
                                //Storing the Array of JSON String to our JSON Array
//                                MYJSON = j.getJSONArray("data");
//                                for (int i = 0; i < MYJSON.length(); i++) {
//                                    try {
//                                        String divnm = "";
//                                        //Getting json object
//                                        JSONObject json = MYJSON.getJSONObject(i);
//                                        divnm = json.getString("NAME");
//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                }


                            } else {

                                Toast.makeText(MainActivity.this, "Wrong User Pass!!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);

    }
}
