package com.example.desquaredproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {


    private ImageButton CalcBtn;
    private ImageButton ConvBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CalcBtn=(ImageButton) findViewById(R.id.CalcBtn);
        ConvBtn=(ImageButton) findViewById(R.id.ConvBtn);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        CalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalculator();
            }
        });

        ConvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConverter();
            }
        });
    }
    public void openCalculator(){
        Intent intent=new Intent(this,CalculatorActivity.class);
        startActivity(intent);
    }
    public void openConverter(){
        Intent intent=new Intent(this,ConverterActivity.class);
        startActivity(intent);
    }
}