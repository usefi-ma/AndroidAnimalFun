package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ForestActivity extends AppCompatActivity {

    ImageView bear;
    ImageView owl;
    ImageView wolf;
    ImageView red_panda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest);

        bear = findViewById(R.id.bear);
        owl = findViewById(R.id.owl);
        wolf = findViewById(R.id.wolf);
        red_panda = findViewById(R.id.red_panda);

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String imageName = getResources().getResourceEntryName(v.getId());
                Log.d("OceanActivity", "imageName: "+ imageName);
                ApiHandler apiHandler = new ApiHandler();
                apiHandler.makeApiCall(imageName, new ApiHandler.ApiCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Intent intent = new Intent(ForestActivity.this, ForestAnimalInfo.class);
                        intent.putExtra("animalName", imageName);
//                        intent.putExtra("animalInfo", response);
                        Log.d("Intent", "putextra:" + intent);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(ForestActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        bear.setOnClickListener(onClickListener);
        owl.setOnClickListener(onClickListener);
        wolf.setOnClickListener(onClickListener);
        red_panda.setOnClickListener(onClickListener);
    }
}