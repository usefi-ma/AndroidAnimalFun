package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FarmInfo extends AppCompatActivity implements ApiHandler.ApiCallback {

    TextView animalName;
    TextView kingdomName;
    TextView slogan;
    TextView prey;
    TextView diet;

    TextView df;
    TextView habitat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_info);

        animalName = findViewById(R.id.animalNameFarm);
        kingdomName = findViewById(R.id.kingdomNameFarm);
        slogan = findViewById(R.id.farmSlogan);
        prey = findViewById(R.id.farmPrey);
        diet = findViewById(R.id.farmDiet);
        df = findViewById(R.id.farmDf);
        habitat = findViewById(R.id.farmHabitat);



        String name = getIntent().getStringExtra("animalName");
        Log.d("FarmInfo", "Name: " + name);
        ApiHandler apiHandler = new ApiHandler();
        apiHandler.makeApiCall(name, FarmInfo.this);
    }

    @Override
    public void onSuccess(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    String Name = jsonArray.getJSONObject(0).getString("name");
                    JSONObject characteristics = jsonArray.getJSONObject(0).getJSONObject("characteristics");
                    String Slogan = characteristics.getString("slogan");
                    String Prey = characteristics.getString("prey");
                    String Diet = characteristics.getString("diet");
                    String Distinctfeature = characteristics.getString("distinctive_feature");
                    String Habitat = characteristics.getString("habitat");

                    animalName.setText(Name);
                    slogan.setText(Slogan);
                    prey.setText(Prey);
                    diet.setText(Diet);
                    df.setText(Distinctfeature);
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
                Toast.makeText(FarmInfo.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}