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
    private String soalJumlah;


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

        String sql = "INSERT INTO soal (kategori, soal, jawab) VALUES ('penjumlahan','4+5','9'),\n" +
                "\t\t('penjumlahan','3+2','5'),\n" +
                "\t\t('penjumlahan','6+2','8'),\n" +
                "\t\t('penjumlahan','5+2','7'),\n" +
                "\t\t('penjumlahan','1+2','3'),\n" +
                "\t\t('penjumlahan','2+4','6'),\n" +
                "\t\t('penjumlahan','1+3','4'),\n" +
                "\t\t('penjumlahan','7+2','9'),\n" +
                "\t\t('penjumlahan','5+2','7'),\n" +
                "\t\t('penjumlahan','2+4','6'),\n" +
                "\t\t\t('pengurangan','9-2','7'),\n" +
                "\t\t\t('pengurangan','8-6','2'),\n" +
                "\t\t\t('pengurangan','7-1','6'),\n" +
                "\t\t\t('pengurangan','3-2','1'),\n" +
                "\t\t\t('pengurangan','9-8','1'),\n" +
                "\t\t\t('pengurangan','10-4','6'),\n" +
                "\t\t\t('pengurangan','7-5','2'),\n" +
                "\t\t\t('pengurangan','10-5','5'),\n" +
                "\t\t\t('pengurangan','9-1','8'),\n" +
                "\t\t\t('pengurangan','10-8','4'),\n" +
                "\t\t\t\t('perkalian','2x4','8'),\n" +
                "\t\t\t\t('perkalian','3x3','9'),\n" +
                "\t\t\t\t('perkalian','6x2','12'),\n" +
                "\t\t\t\t('perkalian','5x2','10'),\n" +
                "\t\t\t\t('perkalian','1x3','3'),\n" +
                "\t\t\t\t('perkalian','3x3','9'),\n" +
                "\t\t\t\t('perkalian','4x2','8'),\n" +
                "\t\t\t\t('perkalian','3x4','12'),\n" +
                "\t\t\t\t('perkalian','5x2','10'),\n" +
                "\t\t\t\t('perkalian','3x2','6'),\n" +
                "\t\t\t\t\t('pembagian','10:2','5'),\n" +
                "\t\t\t\t\t('pembagian','16:2','8'),\n" +
                "\t\t\t\t\t('pembagian','8:4','2'),\n" +
                "\t\t\t\t\t('pembagian','10:5','2'),\n" +
                "\t\t\t\t\t('pembagian','9:3','3'),\n" +
                "\t\t\t\t\t('pembagian','12:2','6'),\n" +
                "\t\t\t\t\t('pembagian','14:2','7'),\n" +
                "\t\t\t\t\t('pembagian','6:3','2'),\n" +
                "\t\t\t\t\t('pembagian','12:6','2'),\n" +
                "\t\t\t\t\t('pembagian','16:2','8'); ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOAL);
        onCreate(db);
    }
    public String soalRandomPenjumlahan(){
        String selectQuery = "SELECT SOAL FROM SOAL WHERE kategori='penjumlahan' ORDER BY RANDOM() LIMIT 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            soalJumlah = cursor.getString(0);
        }
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

    public List<Soal> getAllSoalPenjumlahan() {
        List<Soal> soalList = new ArrayList<Soal>();
        // Select All Query
        String selectQuery = "SELECT * FROM soal WHERE kategori='penjumlahan' ORDER BY RANDOM()";

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

    public List<Soal> getAllSoalPengurangan() {
        List<Soal> soalList = new ArrayList<Soal>();
        // Select All Query
        String selectQuery = "SELECT * FROM soal WHERE kategori='pengurangan' ORDER BY RANDOM()";

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

    public List<Soal> getAllSoalPerkalian() {
        List<Soal> soalList = new ArrayList<Soal>();
        // Select All Query
        String selectQuery = "SELECT * FROM soal WHERE kategori='perkalian' ORDER BY RANDOM()";

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

    public List<Soal> getAllSoalPembagian() {
        List<Soal> soalList = new ArrayList<Soal>();
        // Select All Query
        String selectQuery = "SELECT * FROM soal WHERE kategori='pembagian' ORDER BY RANDOM()";

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

