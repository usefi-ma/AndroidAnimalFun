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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean_animal_info);

        animalName = findViewById(R.id.animalName);
        kingdomName = findViewById(R.id.kingdomName);

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
                    JSONObject taxonomy = jsonArray.getJSONObject(0).getJSONObject("taxonomy");
                    String kingdom = taxonomy.getString("kingdom");

                    animalName.setText(name);
                    kingdomName.setText(kingdom);

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