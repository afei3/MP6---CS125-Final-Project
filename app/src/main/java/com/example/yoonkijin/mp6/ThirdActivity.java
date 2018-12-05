package com.example.yoonkijin.mp6;

import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class ThirdActivity extends AppCompatActivity {
    int firstClick = -1;
    int secondClick = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);




        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (firstClick == -1) {
                    Toast.makeText(ThirdActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                    firstClick = ImageAdapter.mThumbIds[position];
                } else if(secondClick == -1) {
                    secondClick = ImageAdapter.mThumbIds[position];
                    switchImages(firstClick, secondClick);
                    firstClick = -1;
                    secondClick = -1;
                }
            }
        });

    }
    public void switchImages(int image1, int image2) {
        Toast.makeText(ThirdActivity.this, firstClick + " " + secondClick,
                Toast.LENGTH_SHORT).show();
    }

}
