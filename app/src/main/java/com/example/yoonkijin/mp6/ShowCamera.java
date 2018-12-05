package com.example.yoonkijin.mp6;

import android.content.Context;
import android.graphics.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

    Camera camera;

    SurfaceHolder holder;

    public ShowCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }


    /**
     * https://www.youtube.com/watch?v=_wZvds9CfuE
     * tutorial I followed to get an idea of how to use camera(unfinished)
     *
     * (https://developer.android.com/guide/topics/media/camera#custom-camera) the andriod studio
     * website's tutorial, didn't explain well, though
     *
     *
     *
     *
     * I was thinking, in the SecondActivity page, the camera would simply begin running
     * as soon as the user goes to the page(the camera is a FrameLayout) and if the user clicks
     * capture, the app would instantly move into the the ThirdActivity page, the puzzle page,
     * the picture taken already being automatically cut up and turned into a puzzle
     *
     *
     *
     * Also, when testing out, running on emulator, make sure to turn on Camera permission
     * for the app in settings on your emulator, or it won't work
     *
     *
     *
     *
     *
     */











}


