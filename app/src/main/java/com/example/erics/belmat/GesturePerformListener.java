//package com.example.erics.belmat;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.gesture.Gesture;
//import android.gesture.Prediction;
//import android.gesture.GestureLibrary;
//import android.gesture.GestureOverlayView;
//import android.preference.PreferenceManager;
//import android.support.constraint.solver.widgets.Snapshot;
//import android.widget.Toast;
////import android.support.design.widget.*;
//import android.support.design.widget.Snackbar;
//
//import com.example.erics.belmat.helper.Utils;
//
//import java.util.ArrayList;
//
//import static android.content.Context.MODE_PRIVATE;
//
//
//public class GesturePerformListener implements GestureOverlayView.OnGesturePerformedListener {
//
//    private GestureLibrary gestureLibrary = null;
//    String action;
//    int kunci;
//
//    public GesturePerformListener(GestureLibrary gestureLibrary) {
//        this.gestureLibrary = gestureLibrary;
//    }
//
//    /* When GestureOverlayView widget capture a user gesture it will run the code in this method.
//       The first parameter is the GestureOverlayView object, the second parameter store user gesture information.*/
//    @Override
//    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
//
//        // Recognize the gesture and return prediction list.
////        Context applicationContext =  Op_Plus.getContextOfApplication();
////        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
//        SharedPreferences prefs = getSharedPreferences("com.example.erics.belmat", MODE_PRIVATE);
//        String restoredText = prefs.getString("text", null);
//        if (restoredText != null) {
//            int kunci = prefs.getInt("kunci", 0); //0 is the default value.
//        }
//        ArrayList<Prediction> predictionList = gestureLibrary.recognize(gesture);
//
//        int size = predictionList.size();
//
//        if(size > 0)
//        {
//            StringBuffer messageBuffer = new StringBuffer();
//
//            // Get the first prediction.
//            Prediction firstPrediction = predictionList.get(0);
//
//            /* Higher score higher gesture match. */
//            if(firstPrediction.score > 1)
//            {
//                action = firstPrediction.name;
//
//                messageBuffer.append("Anda menulis " + action);
//            }else
//            {
//                messageBuffer.append("Tulisan anda tidak terdeteksi.");
//            }
//
//            // Display a snackbar with related messages.
//            Snackbar snackbar = Snackbar.make(gestureOverlayView, messageBuffer.toString(), Snackbar.LENGTH_LONG);
//            snackbar.show();
//
//        }
//    }
//}
