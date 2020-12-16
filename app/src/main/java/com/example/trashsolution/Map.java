package com.example.trashsolution;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
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
    FoodWasteBucketList bucketList = new FoodWasteBucketList();
    MarkerOptions[] trashLocationMarker = new MarkerOptions[bucketList.getBucketNum()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        String usr_id = intent.getStringExtra("usr_id");
        String usr_password = intent.getStringExtra("usr_password");


        Button QrRead_bt = (Button)findViewById(R.id.QrRead_bt); //QR인식 버튼
        Button MyPage = (Button)findViewById(R.id.myPageButton);

        String[] permissions ={
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
        };
        checkPermissions(permissions);

        //QR인식 버튼 클릭시 기능
        QrRead_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(Map.this, Qr_Scan.class);
                startActivity(myintent);
                //overridePendingTransition(R.anim.fadein, R.anim.fadeout); 효과
            }
        });
        MyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Map.this, UserInfo.class);
                startActivity(intent);
            }
        });


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
                for(int i = 0; i < bucketList.getBucketNum(); i++){
                    showtrashLocationMarker(bucketList.getBucketByIndex(i), i);
                }

            }
        });

        try{
            MapsInitializer.initialize(this);
        }catch (Exception e){
            e.printStackTrace();
        }

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
            long minTime = 5000;
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
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 16));

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
    private void showtrashLocationMarker(FoodWasteBucket bucket, int i) {
        LatLng curPoint = new LatLng(bucket.getLocation().getLatitude(), bucket.getLocation().getLongitude());

        trashLocationMarker[i] = new MarkerOptions();
        trashLocationMarker[i].position(curPoint);
        trashLocationMarker[i].title("쓰레기통"+ i);
        trashLocationMarker[i].snippet("● GPS로 확인한 위치");
        trashLocationMarker[i].icon(BitmapDescriptorFactory.fromResource(R.drawable.trashmarker2));
        Log.d("marker", ""+trashLocationMarker[i].toString());
        map.addMarker(trashLocationMarker[i]);

        View infoWindow = getLayoutInflater().inflate(R.layout.marker_popup, null);
        BucketInfoAdapter bucketInfoAdapter = new BucketInfoAdapter(infoWindow, bucket);
        map.setInfoWindowAdapter(bucketInfoAdapter);
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
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("종료하시겠습니까?");
        builder.setNegativeButton("취소",null);
        builder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);						// 태스크를 백그라운드로 이동
                finishAndRemoveTask();						// 액티비티 종료 + 태스크 리스트에서 지우기
                android.os.Process.killProcess(android.os.Process.myPid());	// 앱 프로세스 종료
            }
        });
        builder.show();
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