package com.example.trashsolution;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class BucketInfoAdapter implements GoogleMap.InfoWindowAdapter {
    View window;
    FoodWasteBucket bucket;

    public BucketInfoAdapter(View window, FoodWasteBucket bucket){
        this.window = window;
        this.bucket = bucket;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        //mapping
        TextView title = window.findViewById(R.id.name);
        TextView remain = window.findViewById(R.id.remain_capacity);
        ImageView imageView = window.findViewById(R.id.imageView);
        if(bucket.getCapacity().getCurrentCapacity() >= 200)
            imageView.setImageResource(R.drawable.fullbucket);
        title.setText(bucket.serialNumber);
        remain.setText(bucket.capacity.currentCapacity + "L / "+ 200+"L");

        Log.d("progress", bucket.getCapacity().getCurrentCapacity() + "");
        return window;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
