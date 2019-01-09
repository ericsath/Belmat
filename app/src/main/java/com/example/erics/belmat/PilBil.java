package com.example.erics.belmat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class PilBil extends Activity {
    Button btnA, btnB, btnC, btnD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pil_bil);

        btnA = (Button)  findViewById(R.id.btn_jum);
        btnB = (Button)  findViewById(R.id.btn_min);
        btnC = (Button)  findViewById(R.id.btn_kali);
        btnD = (Button)  findViewById(R.id.btn_bagi);


        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PilBil.this, Op_Plus.class);
                startActivity(intent);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PilBil.this, Op_Min.class);
                startActivity(intent);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PilBil.this, Op_Kali.class);
                startActivity(intent);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PilBil.this, Op_Bagi.class);
                startActivity(intent);
            }
        });
    }
}
