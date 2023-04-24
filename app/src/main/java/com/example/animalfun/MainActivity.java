package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ApiHandler.ApiCallback {

    Button start;
    TextView apiResponseAnimal;
    TextView apiResponseKingdom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        start=findViewById(R.id.startbtn);
        apiResponseAnimal = findViewById(R.id.animalText);
        apiResponseKingdom = findViewById(R.id.kingdomText);

        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ApiHandler apiHandler = new ApiHandler();
                apiHandler.makeApiCall("Cheetah", MainActivity.this);
            }
        });
    }
    //testing1231

    public void loadMainMenu(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
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

                    apiResponseAnimal.setText(name);
                    apiResponseKingdom.setText(kingdom);

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
                Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}