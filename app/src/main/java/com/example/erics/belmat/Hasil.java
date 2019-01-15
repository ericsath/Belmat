package com.example.erics.belmat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Hasil extends AppCompatActivity {
    private TextView hasil,waktu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        hasil = (TextView) findViewById(R.id.hasil);
        waktu1 = (TextView) findViewById(R.id.waktunya);
        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("skormu",0);
        String waktu = bundle.getString("waktumu","");

        hasil.setText(score+"");
        waktu1.setText(waktu);
    }
}
