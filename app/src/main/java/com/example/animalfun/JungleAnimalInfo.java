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

public class JungleAnimalInfo extends AppCompatActivity implements ApiHandler.ApiCallback{
    TextView animalName;
    TextView kingdomName;
    TextView preyName;
    TextView dietName;
    TextView featureName;
    TextView habitatName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jungle_animal_info);

        animalName = findViewById(R.id.animalName);
        kingdomName = findViewById(R.id.kingdomName);
        preyName = findViewById(R.id.preyName);
        dietName = findViewById(R.id.dietName);
        featureName = findViewById(R.id.featureName);
        habitatName = findViewById(R.id.habitatName);


        String name = getIntent().getStringExtra("animalName");
        Log.d("JungleAnimalInfo", "Name: " + name);
        ApiHandler apiHandler = new ApiHandler();
        apiHandler.makeApiCall(name, JungleAnimalInfo.this);
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
                    String prey = characteristics.getString("prey");
                    String diet = characteristics.getString("diet");
                    String distinctFeature = characteristics.getString("most_distinctive_feature");
                    String habitat = characteristics.getString("habitat");


                    animalName.setText(name);
                    kingdomName.setText(slogan);
                    preyName.setText(prey);
                    dietName.setText(diet);
                    featureName.setText(distinctFeature);
                    habitatName.setText(habitat);


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
                Toast.makeText(JungleAnimalInfo.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
