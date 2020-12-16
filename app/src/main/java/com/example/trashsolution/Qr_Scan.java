package com.example.trashsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class Qr_Scan extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;
    private Button bt_pre, bt_confirm;
    private TextView textView_serialNum;
    TextView tv;
    ProgressDialog dialog = null;

    //qr code scanner object
    private IntentIntegrator qrScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        bt_confirm = (Button) findViewById(R.id.CONFIRM); //확인 버튼
        bt_confirm.setOnClickListener(new Button.OnClickListener() {
                                          @Override
                                          public void onClick(View v){
                                              Intent myintent = new Intent(Qr_Scan.this, Map.class);
                                              startActivity(myintent);
                                              finish();
                                          }
                                      });

        textView_serialNum = (TextView) findViewById(R.id.serialNum); //QR에서 인식된 시리얼넘버를 출력하는 textview

        //Qr코드 스캔 카메라 출력 화면

        qrScan = new IntentIntegrator(this);
        qrScan.setCameraId(0);
        qrScan.setOrientationLocked(false); // default가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.setPrompt("QR코드 인식중...");
        qrScan.initiateScan();

        //디바이스 id 권한 허용
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(Qr_Scan.this,
                    Manifest.permission.READ_PHONE_STATE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(Qr_Scan.this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }


    //스캔 결과를 뽑아냄.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(Qr_Scan.this, "취소!", Toast.LENGTH_SHORT).show();
                Intent myintent = new Intent(Qr_Scan.this, Map.class);
                startActivity(myintent);
//              overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(Qr_Scan.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                textView_serialNum.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


