package com.example.erics.belmat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.erics.belmat.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "soal.db";
    private static final String TABLE_SOAL = "soal";

    private static final String KEY_ID = "idsoal";
    private static final String KEY_KATEGORI = "kategori";
    private static final String KEY_SOAL = "soal";
    private static final String KEY_JAWAB = "jawab";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SOAL = "create table "
                + TABLE_SOAL + "("
                + KEY_ID +" integer primary key autoincrement, "
                + KEY_KATEGORI+ " varchar(50) not null, "
                + KEY_SOAL+ " varchar(50) not null, "
                + KEY_JAWAB+ " varchar(50) not null);";
        db.execSQL(CREATE_SOAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOAL);
        onCreate(db);
    }

    public String soalRandomPenjumlahan(){
        String soalJumlah;
        String selectQuery = "SELECT SOAL FROM SOAL WHERE kategori='penjumlahan' ORDER BY RANDOM() LIMIT 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        soalJumlah = cursor.getString(0);
        cursor.close();
        db.close();
        return soalJumlah;
    }

    public long insertData(Soal soal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.KEY_KATEGORI, soal.getKategori());
        contentValues.put(DatabaseHandler.KEY_SOAL, soal.getSoal());
        contentValues.put(DatabaseHandler.KEY_JAWAB, soal.getJawab());
        long id = db.insert(DatabaseHandler.TABLE_SOAL, null , contentValues);
        db.close();
        return id;
    }

//    public int updateDataSoal(Soal soal){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHandler.KEY_KATEGORI, soal.getKategori());
//        contentValues.put(DatabaseHandler.KEY_SOAL, soal.getSoal());
//        contentValues.put(DatabaseHandler.KEY_JAWAB, soal.getJawab());
//
//        return db.update(TABLE_SOAL,contentValues, KEY_ID + " = ?",
//                new String[]{String.valueOf(soal.getIdSoal())});
//    }

    public List<Soal> getAllSoal() {
        List<Soal> soalList = new ArrayList<Soal>();
        // Select All Query
        String selectQuery = "SELECT * FROM soal";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Soal soal = new Soal();
                soal.setIdSoal(cursor.getInt(0));
                soal.setKategori(cursor.getString(1));
                soal.setSoal(cursor.getString(2));
                soal.setJawab(cursor.getString(3));
                // Adding contact to list
                soalList.add(soal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // close inserting data from database
        db.close();
        // return contact list
        return soalList;
    }

}

