package com.example.erics.belmat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Tent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Tent.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
