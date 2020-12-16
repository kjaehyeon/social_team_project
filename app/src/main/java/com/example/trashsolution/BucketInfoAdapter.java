package com.example.trashsolution;

import android.view.View;

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

        return window;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
