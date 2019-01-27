package com.example.erics.belmat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erics.belmat.database.DatabaseHandler;
import com.example.erics.belmat.model.Soal;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Op_Plus extends Activity {
    Button yha,jwb;
    private GestureLibrary gestureLibrary = null;
    private GestureOverlayView gestureOverlayView = null;
    private TextView soal,timer;
    ArrayList<Soal> soalArray = new ArrayList<Soal>();
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    long waktunya;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;
    public int kunci;
    List<Soal> soals;
    int urutansoal = 0;
    int action;
    int score = 0;
    public static Context contextOfApplication;
    MediaPlayer mp1,mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op__plus);

        Context context = getApplicationContext();
        init(context);
        timer = (TextView) findViewById(R.id.time);
        handler = new Handler() ;
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);

        yha = (Button) findViewById(R.id.halo);
        yha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doit();
            }
        });



        DatabaseHandler db = new DatabaseHandler(this);

        Bundle bundle = getIntent().getExtras();
        String operasi = bundle.getString("operasi","");
        if (operasi.equalsIgnoreCase("tambah")){
            soals = db.getAllSoalPenjumlahan();
        } else if (operasi.equalsIgnoreCase("kurang")){
            soals = db.getAllSoalPengurangan();
        } else if (operasi.equalsIgnoreCase("bagi")){
            soals = db.getAllSoalPembagian();
        } else if (operasi.equalsIgnoreCase("kali")){
            soals = db.getAllSoalPerkalian();
        }

        for (Soal cn : soals) {
            // add contacts data in arrayList
            soalArray.add(cn);
        }

        soal = (TextView) findViewById(R.id.soal);
        soal.setText(soalArray.get(urutansoal).getSoal());
        kunci = Integer.parseInt(soalArray.get(urutansoal).getJawab());


        GesturePerformListener gesturePerformListener = new GesturePerformListener(gestureLibrary);
        gestureOverlayView.addOnGesturePerformedListener(gesturePerformListener);
        contextOfApplication = getApplicationContext();
    }


    private void doit() {
        urutansoal++;
        soal = (TextView) findViewById(R.id.soal);

        if (kunci != action){
            mp2 = MediaPlayer.create(this,R.raw.salah);
            mp2.start();
            Toast.makeText(Op_Plus.this,"SALAH",Toast.LENGTH_SHORT).show();
            if (urutansoal >= 10){
                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                String akhir1 = timer.getText().toString();
                Toast.makeText(Op_Plus.this,"SOAL SUDAH HABIS",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Op_Plus.this,Hasil.class);
                intent.putExtra("skormu",score);
                intent.putExtra("waktumu",akhir1);
                startActivity(intent);
                finish();

                if (urutansoal == 9){
                    yha.setText("lihat hasilmu");
                }
            } else {
                soal.setText(soalArray.get(urutansoal).getSoal());
                kunci = Integer.parseInt(soalArray.get(urutansoal).getJawab());
            }
        } else if (kunci == action){
            score = score+10;
            mp1 = MediaPlayer.create(this,R.raw.benar);
            mp1.start();
            Toast.makeText(Op_Plus.this,"BENAR",Toast.LENGTH_SHORT).show();
            if (urutansoal >= 10){
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                String akhir = timer.getText().toString();

                Toast.makeText(Op_Plus.this,"SOAL SUDAH HABIS",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Op_Plus.this,Hasil.class);
                intent.putExtra("skormu",score);
                intent.putExtra("waktumu",akhir);
                startActivity(intent);
                finish();


                if (urutansoal == 9){
                    yha.setText("lihat hasilmu");
                }
            } else {
                soal.setText(soalArray.get(urutansoal).getSoal());
                kunci = Integer.parseInt(soalArray.get(urutansoal).getJawab());
            }
        }

    }

    /* Initialise class or instance variables. */
    private void init(Context context)
    {
        if(gestureLibrary == null)
        {
            // Load custom gestures from gesture.txt file.
            gestureLibrary = GestureLibraries.fromRawResource(context, R.raw.gesture);

            if(!gestureLibrary.load())
            {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setMessage("Custom gesture file load failed.");
                alertDialog.show();

                finish();
            }
        }

        if(gestureOverlayView == null)
        {
            gestureOverlayView = (GestureOverlayView)findViewById(R.id.gesture_overlay_view);
        }
    }
    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%02d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

    public class GesturePerformListener implements GestureOverlayView.OnGesturePerformedListener {

        private GestureLibrary gestureLibrary = null;

        public GesturePerformListener(GestureLibrary gestureLibrary) {
            this.gestureLibrary = gestureLibrary;
        }

        @Override
        public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {

            ArrayList<Prediction> predictionList = gestureLibrary.recognize(gesture);

            int size = predictionList.size();

            if(size > 0)
            {
                StringBuffer messageBuffer = new StringBuffer();

                // Get the first prediction.
                Prediction firstPrediction = predictionList.get(0);

                /* Higher score higher gesture match. */
                if(firstPrediction.score > 1)
                {
                    action = Integer.parseInt(firstPrediction.name);

                    messageBuffer.append("Anda menulis " + action);
                }else
                {
                    messageBuffer.append("Tulisan anda tidak terdeteksi.");
                }

                // Display a snackbar with related messages.
                Snackbar snackbar = Snackbar.make(gestureOverlayView, messageBuffer.toString(), Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(contextOfApplication, "Selesaikan dulu soalnya", Toast.LENGTH_SHORT).show();
    }
}