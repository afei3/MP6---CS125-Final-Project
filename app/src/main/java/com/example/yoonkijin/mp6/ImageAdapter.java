package com.example.yoonkijin.mp6;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return mThumbIds[position];
    }

    public static void setItemId(int position, int newId) {
        mThumbIds[position] = newId;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    public static String[] imageNames = {
        "aaa.png", "bbb.png",
        "ccc.png", "ddd.png",
        "eee.png", "fff.png",
        "ggg.png", "hhh.png",
        "iii.png"

    };

    // references to our images
    public static Integer[] mThumbIds = {
            R.drawable.aaa, R.drawable.bbb,
            R.drawable.ccc, R.drawable.ddd,
            R.drawable.eee, R.drawable.fff,
            R.drawable.ggg, R.drawable.hhh,
            R.drawable.iii
    };

    public static Integer[] solved = {
            R.drawable.eee, R.drawable.hhh,
            R.drawable.ggg, R.drawable.ddd,
            R.drawable.aaa, R.drawable.fff,
            R.drawable.ccc, R.drawable.bbb,
            R.drawable.iii
    };

}
