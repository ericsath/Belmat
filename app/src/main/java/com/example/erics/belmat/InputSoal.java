package com.example.erics.belmat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erics.belmat.database.DatabaseHandler;

public class InputSoal extends AppCompatActivity {
    private EditText id,kat,soal,jwbn;
    private DatabaseHandler databaseHandler;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_soal);

        viewdata();

        id = (EditText) findViewById(R.id.idSoal);
        kat = (EditText) findViewById(R.id.idKategori);
        soal = (EditText) findViewById(R.id.soal);
        jwbn = (EditText) findViewById(R.id.jawab);
        ok = (Button) findViewById(R.id.tambah);

        databaseHandler = new DatabaseHandler(this);
    }

    private void viewdata() {
        String data = databaseHandler.getData();
        Toast.makeText(InputSoal.this,data,Toast.LENGTH_SHORT).show();
    }

    public void addUser (View view){
        id.getText().toString();
        kat.getText().toString();
        soal.getText().toString();
        jwbn.getText().toString();

    }
}
