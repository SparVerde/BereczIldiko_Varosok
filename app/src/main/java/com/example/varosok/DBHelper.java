package com.example.varosok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {
    //1. változók definiálása
    public static final String DB_NAME ="varosok.db";
    //egy protokol az int-el amit mindíg végig kell vinnünk
    public static final int DB_VERSION =1;

    //1.2.tábla létrehozás és benne oszlopok (COL)
    public static final String VAROSOK_TABLA = "varosok";

    public static final String COL_ID = "id";
    public static final String COL_NEV = "nev";
    public static final String COL_ORSZAG = "orszag";
    public static final String COL_LAKOSSAG = "lakossag";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS "+ VAROSOK_TABLA + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NEV + " VARCHAR(255) UNIQUE NOT NULL," +
                COL_ORSZAG + " VARCHAR(255) NOT NULL," +
                COL_LAKOSSAG + " INTEGER NOT NULL" +
                ")";
        //futtatás:
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + VAROSOK_TABLA;
        //ha változás történik a az adatbázisben újra meg kell hívni.
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    //2. konstruktorokat hozzuk létre:
    //DB_VERSION az upgrade
    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //3. adatrögzítés metódus: insert boolean-el, bemeneti értékei, név, orszag, lakossag
    // -->majd létre hozzuk az adatmezőket az InsertActivityben --> és végül a főmenüben kiolvassuk
    public boolean insert(String nev, String orszag, String lakossag){
//SQliteDatabase változó kell amibe írunk
        SQLiteDatabase db = this.getWritableDatabase();
//Content values is olyan mint  stringBuilder csak kulcs-érték párokat adunk meg pl. COL_NEV - nev
        ContentValues values = new ContentValues();
        values.put(COL_NEV, nev);
        values.put(COL_ORSZAG, orszag);
        values.put(COL_LAKOSSAG, lakossag);
        return db.insert(VAROSOK_TABLA, null, values) !=-1;
    }

    //4. adatlekérdezés metodus:
    // -->majd létre hozzuk az adatmezőket a SearchResultActivityben --> és végül a főmenüben kiolvassuk
    public Cursor SearchResult(){
        //itt is létre kell hozni az SQLiteDatabase-t
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(VAROSOK_TABLA, new String[]{COL_ID, COL_NEV, COL_ORSZAG, COL_LAKOSSAG},
                null, null, null, null, null);
    }
}
