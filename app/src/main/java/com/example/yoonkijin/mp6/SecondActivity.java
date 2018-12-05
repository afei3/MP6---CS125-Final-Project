package com.example.yoonkijin.mp6;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class SecondActivity extends AppCompatActivity {


    //Camera camera;

    FrameLayout frameLayout;

    ShowCamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        //open camera
        //camera = Camera.open();


        //This line is needed, that is, according to the tutorial, but seems to give an error, not sure why
//        showCamera = new ShowCamera(this, camera);








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

                Intent goToThirdPage = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(goToThirdPage);

            }
        });



    }



}
