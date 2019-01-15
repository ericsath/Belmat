package com.example.erics.belmat;

import android.app.Activity;
import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
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

import java.util.ArrayList;
import java.util.List;

public class Op_Plus extends Activity implements GestureOverlayView.OnGesturePerformedListener {
    Button yha,jwb;
    GestureLibrary gestureLib;
    private TextView soal,timer;
    ArrayList<Soal> soalArray = new ArrayList<Soal>();
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;
    public int kunci;
    List<Soal> soals;
    int urutansoal = 1;
    String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
        View inflate = getLayoutInflater().inflate(R.layout.activity_op__plus, null);
        gestureOverlayView.addView(inflate);
        gestureOverlayView.addOnGesturePerformedListener(this);
        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gesture);
        if (!gestureLib.load()) {
            finish();
        }
        setContentView(gestureOverlayView);
//        setContentView(R.layout.activity_op__plus);

        Context context = getApplicationContext();
//        init(context);
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

        jwb = (Button) findViewById(R.id.jwb);
        jwb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jwbit();
            }
        });

        DatabaseHandler db = new DatabaseHandler(this);
        soals = db.getAllSoalPenjumlahan();
        for (Soal cn : soals) {
            // add contacts data in arrayList
            soalArray.add(cn);
        }
        soal = (TextView) findViewById(R.id.soal);
        soal.setText(soalArray.get(urutansoal).getSoal());
        kunci = Integer.parseInt(soalArray.get(urutansoal).getJawab());

//        Toast.makeText(Op_Plus.this,kunci+"",Toast.LENGTH_SHORT).show();

    }

    private void doit() {
        /*DatabaseHandler db = new DatabaseHandler(this);
        soals = db.getAllSoalPenjumlahan();

        for (Soal cn : soals) {
            // add contacts data in arrayList
            soalArray.add(cn);
        }*/
        urutansoal++;
        soal = (TextView) findViewById(R.id.soal);
//        soal.setText(db.getAllSoalPenjumlahan().get(urutansoal).getSoal());
//        kunci = Integer.parseInt(db.getAllSoalPenjumlahan().get(urutansoal).getJawab());
        soal.setText(soalArray.get(urutansoal).getSoal());
        kunci = Integer.parseInt(soalArray.get(urutansoal).getJawab());

        Log.d("KUNCI", "doit: "+kunci);

        TimeBuff += MillisecondTime;

        handler.removeCallbacks(runnable);

    }
    private void jwbit(){
        if (kunci != Integer.parseInt(action)){
            Toast.makeText(Op_Plus.this,"SALAH",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Op_Plus.this,"BENAR",Toast.LENGTH_SHORT).show();
        }
//

        TimeBuff += MillisecondTime;

        handler.removeCallbacks(runnable);
    }

    /* Initialise class or instance variables. */
//    private void init(Context context)
//    {
//        if(gestureLibrary == null)
//        {
//            // Load custom gestures from gesture.txt file.
//            gestureLibrary = GestureLibraries.fromRawResource(context, R.raw.gesture);
//
//            if(!gestureLibrary.load())
//            {
//                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//                alertDialog.setMessage("Custom gesture file load failed.");
//                alertDialog.show();
//
//                finish();
//            }
//        }
//
//        if(gestureOverlayView == null)
//        {
//            gestureOverlayView = (GestureOverlayView)findViewById(R.id.gesture_overlay_view);
//        }
//    }
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

//    public GesturePerformListener(GestureLibrary gestureLibrary) {
//        this.gestureLib = gestureLibrary;
//    }

    @Override
    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
        // Recognize the gesture and return prediction list.
        ArrayList<Prediction> predictionList = gestureLib.recognize(gesture);

        int size = predictionList.size();

        if(size > 0)
        {
            StringBuffer messageBuffer = new StringBuffer();

            // Get the first prediction.
            Prediction firstPrediction = predictionList.get(0);

            /* Higher score higher gesture match. */
            if(firstPrediction.score > 1)
            {
                action = firstPrediction.name;

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