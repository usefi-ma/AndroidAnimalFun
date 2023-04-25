package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class FarmActivity extends AppCompatActivity  {

    ImageView cow;
    ImageView chicken;
    ImageView pig;
    ImageView horse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        horse = findViewById(R.id.horse);
        cow = findViewById(R.id.cow);
        pig = findViewById(R.id.pig);
        chicken = findViewById(R.id.chicken);


        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String imageName = getResources().getResourceEntryName(v.getId());
                Log.d("FarmActivity", "imageName: "+ imageName);
                ApiHandler apiHandler = new ApiHandler();
                apiHandler.makeApiCall(imageName, new ApiHandler.ApiCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Intent intent = new Intent(FarmActivity.this, FarmInfo.class);
                        intent.putExtra("animalName", imageName);
//                        intent.putExtra("animalInfo", response);
                        Log.d("Intent", "putextra:" + intent);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(FarmActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        cow.setOnClickListener(onClickListener);
        horse.setOnClickListener(onClickListener);
        pig.setOnClickListener(onClickListener);
        chicken.setOnClickListener(onClickListener);
    }
}

