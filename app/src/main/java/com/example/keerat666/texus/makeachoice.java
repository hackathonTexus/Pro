package com.example.keerat666.texus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class makeachoice extends AppCompatActivity {

    public void gotoupload(View view)
    {
        Intent x=new Intent(getApplicationContext(),choose.class);
        startActivity(x);
    }

    public void gotostream(View view)
    {
        Intent x=new Intent(getApplicationContext(),choose.class);
        startActivity(x);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeachoice);
    }
}
