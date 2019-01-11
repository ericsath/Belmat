package com.example.erics.belmat;

import android.app.Activity;
import android.content.Context;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erics.belmat.database.DatabaseHandler;
import com.example.erics.belmat.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class Op_Plus extends Activity {
    private GestureOverlayView gestureOverlayView = null;

    private GestureLibrary gestureLibrary = null;
    private TextView soal;
    ArrayList<Soal> soalArray = new ArrayList<Soal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op__plus);

        Context context = getApplicationContext();
        init(context);

        DatabaseHandler db = new DatabaseHandler(this);
        List<Soal> soals = db.getAllSoalPenjumlahan();
        for (Soal cn : soals) {
            // add contacts data in arrayList
            soalArray.add(cn);
        }
        soal = (TextView) findViewById(R.id.soal);
        soal.setText(soalArray.indexOf(0));

        GesturePerformListener gesturePerformListener = new GesturePerformListener(gestureLibrary);
        gestureOverlayView.addOnGesturePerformedListener(gesturePerformListener);
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
}