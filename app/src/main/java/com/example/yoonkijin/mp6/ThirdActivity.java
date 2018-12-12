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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;


public class ThirdActivity extends AppCompatActivity {
    // Instantiate the RequestQueue.
    String url;
    RequestQueue queue;

    private String TAG = "MyTag";
    RequestQueue mRequestQueue;

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
        queue = Volley.newRequestQueue(this);
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
            long time = timer.getElapsedTimeSecs();
            startAPICall(time);

        }
    }

    DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                Intent restart = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(restart);
                break;
        }
    };

    private void showDialog(Long time) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThirdActivity.this);
        builder.setMessage("You solved the puzzle in " + String.valueOf(time) + " seconds!\n" +
                "Here is a fact about " + String.valueOf(time) + "\n" + TAG)
                .setPositiveButton("Go Back", dialogClickListener)
                .setNegativeButton("Replay", dialogClickListener).show();
    }

    private void startAPICall(long time) {
        try {
            StringRequest jsonObjectRequest = new StringRequest(
                    Request.Method.GET,
                    "http://numbersapi.com/" + time,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String response) {
                            Log.d(TAG, response);
                            TAG = response;
                            showDialog(time);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
