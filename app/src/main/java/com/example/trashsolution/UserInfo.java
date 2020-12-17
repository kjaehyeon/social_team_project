package com.example.trashsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Intent intent = getIntent();

        Customer customer = (Customer) intent.getSerializableExtra("customer");

        TextView userID1 = (TextView) findViewById(R.id.userID);
        TextView userID2 = (TextView) findViewById(R.id.userId);
        TextView usage = (TextView) findViewById(R.id.userTotalUsage);
        TextView address = (TextView) findViewById(R.id.userAddress);
        TextView monthlyUsage = (TextView) findViewById(R.id.thisMonthUsage);

        userID1.setText(customer.getId());
        userID2.setText(customer.getId());
        usage.setText(Double.toString(customer.getsUsage().totalUsage) + " L");
        address.setText(customer.getAddress());
        monthlyUsage.setText("이번 달 사용량은 " + Double.toString(customer.getsUsage().monthlyUsage) + " L 입니다");
    }
}