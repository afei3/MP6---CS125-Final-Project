package com.example.yoonkijin.mp6;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class SecondActivity extends AppCompatActivity {

    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);


        //backButton - leads to FirstActivity screen - Home page
        Button backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);

            }
        });


        //cameraButton -
        Button cameraButton = (Button) findViewById(R.id.cameraButton);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureImage = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(captureImage);

            }
        });



    }
}
