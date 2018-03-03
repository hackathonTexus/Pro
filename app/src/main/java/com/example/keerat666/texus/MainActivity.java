package com.example.keerat666.texus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.keerat666.texus.R.id.pw;

public class MainActivity extends AppCompatActivity {

    public void transfer(View view)
    {
        EditText editText = (EditText)findViewById(R.id.pw);
        String editTextStr = editText.getText().toString();
        //Toast.makeText(this, ""+editTextStr, Toast.LENGTH_SHORT).show();
       Intent x=new Intent(getApplicationContext(),makeachoice.class);
       startActivity(x);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
