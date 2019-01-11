package com.example.erics.belmat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.erics.belmat.model.Soal;

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
                + KEY_JAWAB+ " varchar(50) not null, "
                + KEY_SOAL+ " varchar(50) not null);";
        db.execSQL(CREATE_SOAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOAL);
        onCreate(db);
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

    public int updateDataSoal(Soal soal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.KEY_KATEGORI, soal.getKategori());
        contentValues.put(DatabaseHandler.KEY_SOAL, soal.getSoal());
        contentValues.put(DatabaseHandler.KEY_JAWAB, soal.getJawab());

        return db.update(TABLE_SOAL,contentValues, KEY_ID + " = ?",
                new String[]{String.valueOf(soal.getIdSoal())});
    }

}

