package com.example.erics.belmat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


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
                intent.putExtra("operasi","tambah");
                startActivity(intent);
                finish();
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PilBil.this, Op_Plus.class);
                intent.putExtra("operasi","kurang");
                startActivity(intent);
                finish();
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PilBil.this, Op_Plus.class);
                intent.putExtra("operasi","kali");
                startActivity(intent);
                finish();
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PilBil.this, Op_Plus.class);
                intent.putExtra("operasi","bagi");
                startActivity(intent);
                finish();
            }
        });
    }
}
