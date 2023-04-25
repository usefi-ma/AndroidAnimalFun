package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class OceanActivity extends AppCompatActivity {

    ImageView seahorse;
    ImageView starfish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean);

        seahorse = findViewById(R.id.seahorse);
        starfish = findViewById(R.id.starfish);
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String imageName = getResources().getResourceEntryName(v.getId());
                Log.d("OceanActivity", "imageName: "+ imageName);
                ApiHandler apiHandler = new ApiHandler();
                apiHandler.makeApiCall(imageName, new ApiHandler.ApiCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Intent intent = new Intent(OceanActivity.this, OceanAnimalInfo.class);
                        intent.putExtra("animalName", imageName);
//                        intent.putExtra("animalInfo", response);
                        Log.d("Intent", "putextra:" + intent);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(OceanActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        seahorse.setOnClickListener(onClickListener);
        starfish.setOnClickListener(onClickListener);
    }
}