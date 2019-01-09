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

    private static final String db_create = "create table "
            + TABLE_SOAL + "("
            + KEY_ID +" integer primary key autoincrement, "
            + KEY_KATEGORI+ " varchar(50) not null, "
            + KEY_JAWAB+ " varchar(50) not null, "
            + KEY_SOAL+ " varchar(50) not null);";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SOAL);
        onCreate(sqLiteDatabase);
    }

    public long insertData(int idnya, String kategori, String soal, String jawab)
    {
        SQLiteDatabase db = DatabaseHandler.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.KEY_ID, idnya);
        contentValues.put(DatabaseHandler.KEY_KATEGORI, kategori);
        contentValues.put(DatabaseHandler.KEY_SOAL, soal);
        contentValues.put(DatabaseHandler.KEY_JAWAB, jawab);
        long id = db.insert(DatabaseHandler.TABLE_SOAL, null , contentValues);
        return id;
    }


//    public void addRecord(Soal soal){
//        SQLiteDatabase db  = getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_ID, soal.getIdSoal());
//        values.put(KEY_KATEGORI, soal.getKategori());
//        values.put(KEY_SOAL, soal.getSoal());
//        values.put(KEY_JAWAB, soal.getJawab());
//
//        db.insert(TABLE_SOAL, null, values);
//        db.close();
//    }
}

