package com.example.yoonkijin.mp6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;


public class ThirdActivity extends AppCompatActivity {
    // Instantiate the RequestQueue.
    String url ="http://www.google.com";

    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;

    private TextView mTextView;

    StopWatch timer = new StopWatch();
    final int REFRESH_RATE = 100;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_TIMER:
                    timer.start(); //start timer
                    mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;

                case MSG_UPDATE_TIMER:
                    mTextView.setText(""+ timer.getElapsedTime());
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                    break;                                  //though the timer is still running
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    timer.stop();//stop timer
                    mTextView.setText(""+ timer.getElapsedTime());
                    break;

                default:
                    break;
            }
        }
    };

    int counter = 0;
    int firstClick = -1;
    int secondClick = -1;
    int position1 = -1;
    int position2 = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mHandler.sendEmptyMessage(MSG_START_TIMER);
        mTextView = (TextView) findViewById(R.id.Timer);
        Collections.shuffle(Arrays.asList(ImageAdapter.mThumbIds));
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setVerticalSpacing(85);
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
        counter++;
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        boolean isSolved = true;
        for (int i = 0; i < ImageAdapter.mThumbIds.length; i++) {
            if (!ImageAdapter.mThumbIds[i].equals(ImageAdapter.solved[i])) {
                isSolved = false;
            }
        }
        if (isSolved) {
            mHandler.sendEmptyMessage(MSG_STOP_TIMER);
            AlertDialog.Builder builder = new AlertDialog.Builder(ThirdActivity.this);
            builder.setMessage("You solved the puzzle in " + timer.getElapsedTimeSecs() + " seconds!")
                    .setPositiveButton("Go Back", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
            Toast.makeText(ThirdActivity.this,"You solved the puzzle", Toast.LENGTH_SHORT).show();
        }
    }

    DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            case DialogInterface.BUTTON_NEGATIVE:
                break;
        }
    };

}
