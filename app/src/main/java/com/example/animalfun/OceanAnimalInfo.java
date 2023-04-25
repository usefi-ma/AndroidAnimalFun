package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OceanAnimalInfo extends AppCompatActivity implements ApiHandler.ApiCallback{

    TextView animalName;
    TextView kingdomName;
    TextView Prey;
    TextView Diet;
    TextView DistinctFeature;
    TextView Habitat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean_animal_info);

        animalName = findViewById(R.id.animalName);
        kingdomName = findViewById(R.id.kingdomName);
        Prey = findViewById(R.id.prey);
        Diet = findViewById(R.id.diet);
        DistinctFeature = findViewById(R.id.distinctfeature);
        Habitat = findViewById(R.id.habitat);

        String name = getIntent().getStringExtra("animalName");
        Log.d("OceanAnimalInfo", "Name: " + name);
        ApiHandler apiHandler = new ApiHandler();
        apiHandler.makeApiCall(name, OceanAnimalInfo.this);
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
                    String prey = characteristics.getString("main_prey");
                    String diet = characteristics.getString("diet");
                    String distinctfeature = characteristics.getString("distinctive_feature");
                    String habitat = characteristics.getString("habitat");


                    animalName.setText(name);
                    kingdomName.setText(slogan);
                    Prey.setText(prey);
                    Diet.setText(diet);
                    DistinctFeature.setText(distinctfeature);
                    Habitat.setText(habitat);

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
                Toast.makeText(OceanAnimalInfo.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}