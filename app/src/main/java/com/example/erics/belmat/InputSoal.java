package com.example.erics.belmat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.erics.belmat.database.DatabaseHandler;
import com.example.erics.belmat.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class InputSoal extends AppCompatActivity {
    private final static String TAG ="logv"+InputSoal.class.getSimpleName();
    String id,kategori,soal,jwbn;
    private DatabaseHandler db = new DatabaseHandler(this);
    private Button ok;
    ArrayList<Soal> soalArray = new ArrayList<Soal>();
    SoalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_soal);

        List<Soal> soals = db.getAllSoal();
        for (Soal cn : soals) {
            // add contacts data in arrayList
            soalArray.add(cn);

        }
        adapter = new SoalAdapter(this, R.layout.rv_soal,
                soalArray);
        final ListView dataList = (ListView) findViewById(R.id.recyc);
        dataList.setAdapter(adapter);

        final EditText inputkat = (EditText) findViewById(R.id.idKategori);
        final EditText inputsoal = (EditText) findViewById(R.id.soal);
        final EditText inputjwbn = (EditText) findViewById(R.id.jawab);
        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kategori = inputkat.getText().toString();
                soal = inputsoal.getText().toString();
                jwbn = inputjwbn.getText().toString();

                if (inputkat.equals("")||inputsoal.equals("")||inputjwbn.equals("")){
                    Toast.makeText(getApplicationContext(),"Tolong dilengkapi",Toast.LENGTH_SHORT).show();
                }else{
                    db.insertData(new Soal(kategori, soal, jwbn));
                    Toast.makeText(getApplicationContext(),"sukses input ke database ", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: "+db.getReadableDatabase());
                    inputkat.setText("");
                    inputsoal.setText("");
                    inputjwbn.setText("");
                }
            }
        });

    }

}
