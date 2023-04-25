package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForestAnimalInfo extends AppCompatActivity implements ApiHandler.ApiCallback{

    TextView forestAnimalName;
    TextView forestAnimalSlogan;
    TextView forestDF;
    TextView diet;
    TextView prey;
    TextView habitat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest_animal_info);

        forestAnimalName = findViewById(R.id.forestAnimalName);
        forestAnimalSlogan = findViewById(R.id.forestAnimalSlogan);
        forestDF = findViewById(R.id.forestDF);
        diet = findViewById(R.id.diet);
        prey = findViewById(R.id.prey);
        habitat = findViewById(R.id.habitat);

        String name = getIntent().getStringExtra("animalName");
        Log.d("OceanAnimalInfo", "Name: " + name);
        ApiHandler apiHandler = new ApiHandler();
        apiHandler.makeApiCall(name, ForestAnimalInfo.this);
    }

    @Override
    public void onSuccess(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    String name = jsonArray.getJSONObject(0).getString("name");
                    JSONObject characteristics = jsonArray.getJSONObject(0).getJSONObject("characteristics");
                    String slogan = characteristics.getString("slogan");
                    String DF = characteristics.getString("most_distinctive_feature");
                    String Diet = characteristics.getString("diet");
                    String Prey = characteristics.getString("prey");
                    String Habitat = characteristics.getString("habitat");


                    forestAnimalName.setText(name);
                    forestAnimalSlogan.setText(slogan);
                    forestDF.setText(DF);
                    diet.setText(Diet);
                    prey.setText(Prey);
                    habitat.setText(Habitat);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onError(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ForestAnimalInfo.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}