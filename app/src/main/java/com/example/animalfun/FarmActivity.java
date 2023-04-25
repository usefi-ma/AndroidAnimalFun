package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

public class FarmActivity extends AppCompatActivity implements ApiHandler.ApiCallback   {

    TextView farmTest;

    Button cow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        ApiHandler apiHandler = new ApiHandler();
        //farmTest = findViewById(R.id.farmTest);
        //cow = findViewById(R.id.btnCow);



        cow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ApiHandler apiHandler = new ApiHandler();
                apiHandler.makeApiCall("Cow", FarmActivity.this);
            }
        });
                                 }
                //testing1231

        @Override
        public void onSuccess(final String response) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        String name = jsonArray.getJSONObject(0).getString("name");
                        JSONObject taxonomy = jsonArray.getJSONObject(0).getJSONObject("taxonomy");
                        String kingdom = taxonomy.getString("kingdom");


                        //farmTest.setText(name);
//                      apiResponseKingdom.setText(kingdom);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onError(final String message) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FarmActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

