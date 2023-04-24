package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button farm;
    Button ocean;
    Button forest;
    Button jungle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        farm = findViewById(R.id.btnFarm);
        ocean = findViewById(R.id.btnOcean);
        forest = findViewById(R.id.btnForest);
        jungle = findViewById(R.id.btnJungle);
    }

    public void openFarm(View view) {

        Intent intent = new Intent(this, FarmActivity.class);
        startActivity(intent);
    }

    public void openOcean(View view) {
        Intent intent = new Intent(this, OceanActivity.class);
        startActivity(intent);
    }

    public void openForest(View view) {
        Intent intent = new Intent(this, ForestActivity.class);
        startActivity(intent);
    }

    public void openJungle(View view) {
        Intent intent = new Intent(this, JungleActivity.class);
        startActivity(intent);
    }
}