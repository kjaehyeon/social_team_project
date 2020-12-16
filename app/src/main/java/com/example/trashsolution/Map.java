package com.example.trashsolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Map extends AppCompatActivity{
    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions myLocationMarker;
    MarkerOptions[] trashLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        String usr_id = intent.getStringExtra("usr_id");
        //OOO님 환영합니다!
        String usr_password = intent.getStringExtra("usr_password");
        trashLocationMarker = new MarkerOptions[5];
        String[] permissions ={
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        checkPermissions(permissions);

        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map","지도 준비됨");
                map = googleMap;
                try{
                    map.setMyLocationEnabled(true);
                }catch (SecurityException se){
                    se.printStackTrace();
                }

                LatLng point = new LatLng(35.885870, 128.605367);
                showtrashLocationMarker(point , 0);
                LatLng point2 = new LatLng(35.884851, 128.607256);
                showtrashLocationMarker(point2 , 1);
                LatLng point3 = new LatLng(35.884236, 128.608721);
                showtrashLocationMarker(point3 , 2);
                LatLng point4 = new LatLng(35.884708, 128.611076);
                showtrashLocationMarker(point4 , 3);
                LatLng point5 = new LatLng(35.885159, 128.612417);
                showtrashLocationMarker(point5 , 4);
            }
        });

        try{
            MapsInitializer.initialize(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), usr_id+"님, 환영합니다!", Toast.LENGTH_LONG).show();

        startLocationService();


    }
    public void startLocationService(){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try{
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 1000;
            float minDistance = 0;

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime,minDistance, gpsListener);


        }catch(SecurityException se){
            se.getStackTrace();
        }
    }



    class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            showMyCurrentLocation(latitude,longitude);


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }
    private void showMyCurrentLocation(Double latitude, Double longitude){
        LatLng curPoint = new LatLng(latitude,longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 16));

        showMyLocationMarker(curPoint);



    }
    private void showMyLocationMarker(LatLng curPoint) {
        if (myLocationMarker == null) {
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("● 내 위치\n");
            myLocationMarker.snippet("● GPS로 확인한 위치");
            //myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.trashmarker2));
            map.addMarker(myLocationMarker);
        } else {
            myLocationMarker.position(curPoint);
        }
    }
    private void showtrashLocationMarker(LatLng curPoint, int i) {
        if (trashLocationMarker[i] == null) {
            trashLocationMarker[i] = new MarkerOptions();
            trashLocationMarker[i].position(curPoint);
            trashLocationMarker[i].title("● 내 위치\n");
            trashLocationMarker[i].snippet("● GPS로 확인한 위치");
            trashLocationMarker[i].icon(BitmapDescriptorFactory.fromResource(R.drawable.trashmarker2));
            map.addMarker(trashLocationMarker[i]);

            View infoWindow = getLayoutInflater().inflate(R.layout.marker_popup, null);
            BucketInfoAdapter bucketInfoAdapter = new BucketInfoAdapter(infoWindow, )
        } else {
            trashLocationMarker[i].position(curPoint);
        }
    }

    public void checkPermissions(String[] permissions){
        ArrayList<String> targetList = new ArrayList<String>();

        for(int i = 0; i <permissions.length; i++){
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this,curPermission);
            if(permissionCheck == PackageManager.PERMISSION_GRANTED){
                Log.d("permission","granted");
            }else{
                Log.d("permission","not granted");
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,curPermission)){
                    Log.d("permission","need explanation");
                }else{
                    targetList.add(curPermission);
                }
            }
        }

        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);
        if(targets.length != 0)
            ActivityCompat.requestPermissions(this,targets,101);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 101:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this,"첫 번째 권한 승인함",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"첫 번째 권한 거부됨",Toast.LENGTH_SHORT).show();
                return;
        }
    }
    public void onResume(){
        super.onResume();
        try{
            if(map != null){
                map.setMyLocationEnabled(true);
            }
        }catch (SecurityException se){
            se.printStackTrace();
        }

    }
    public void onPause(){
        super.onPause();
        try{
            if(map != null){
                map.setMyLocationEnabled(false);
            }
        }catch (SecurityException se){
            se.printStackTrace();
        }
    }
}