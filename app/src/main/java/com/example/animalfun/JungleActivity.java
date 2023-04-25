package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class JungleActivity extends AppCompatActivity {

    ImageView panther;
    ImageView tiger;
    ImageView toucan;
    ImageView gorilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jungle);

        panther = findViewById(R.id.panther);
        tiger = findViewById(R.id.tiger);
        toucan = findViewById(R.id.toucan);
        gorilla = findViewById(R.id.gorilla);
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String imageName = getResources().getResourceEntryName(v.getId());
                Log.d("JungleActivity", "imageName: "+ imageName);
                ApiHandler apiHandler = new ApiHandler();
                apiHandler.makeApiCall(imageName, new ApiHandler.ApiCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Intent intent = new Intent(JungleActivity.this, OceanAnimalInfo.class);
                        intent.putExtra("animalName", imageName);
//                        intent.putExtra("animalInfo", response);
                        Log.d("Intent", "putextra:" + intent);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(JungleActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        panther.setOnClickListener(onClickListener);
        tiger.setOnClickListener(onClickListener);
        toucan.setOnClickListener(onClickListener);
        gorilla.setOnClickListener(onClickListener);
    }
}