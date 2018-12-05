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
    int position1 = -1;
    int position2 = -1;


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
                    position1 = position;
                    firstClick = ImageAdapter.mThumbIds[position];
                } else if(secondClick == -1) {
                    position2 = position;
                    secondClick = ImageAdapter.mThumbIds[position];
                    switchImages();
                    firstClick = -1;
                    secondClick = -1;
                }
            }
        });

    }
    public void switchImages() {
        int temp = firstClick;
        ImageAdapter.setItemId(position1, secondClick);
        ImageAdapter.setItemId(position2, temp);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }

}
