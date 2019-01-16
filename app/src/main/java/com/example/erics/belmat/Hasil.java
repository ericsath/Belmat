package com.example.erics.belmat;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        hasil = (TextView) findViewById(R.id.nilai);
        waktu1 = (TextView) findViewById(R.id.waktu);
        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("skormu",0);
        String waktu = bundle.getString("waktumu","");

        hasil.setText(score+"");
        waktu1.setText(waktu);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Selesai");
        builder.setMessage("Mau belajar lagi ? ");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Hasil.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("Keluar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                moveTaskToBack(true);
            }
        });
        builder.show();
    }
}
