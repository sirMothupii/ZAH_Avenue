package com.sirmothupii.zah_avenue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Register extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    //Intent to take user to next screen on button click
    public void onClickSubmit(View view)
    {
        Intent intent1 = new Intent(this, LoginActivity.class);
        startActivity(intent1);
    }
}
