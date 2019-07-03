package com.adrian.songchooser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ManageDatabase extends SQLiteOpenHelper {

    private static ManageDatabase sInstance;

    private static final String DATABASE_NAME = "playlist,db";
    private static final String DATABASE_TABLE = "playlist";
    private static final int DATABASE_VERSION = 1;

    public static synchronized ManageDatabase getInstance(Context context){
        if(sInstance == null){
            sInstance = new ManageDatabase((context.getApplicationContext()));
        }
        return sInstance;
    }

    public ManageDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
            "create table playlist(" +
                    "nr integer primary key autoincrement," +
                    "artist text," +
                    "album text," +
                    "song text);" +
                    "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addSong(String artist, String album, String song){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("artist",artist);
        values.put("album",album);
        values.put("song",song);
        db.insertOrThrow(DATABASE_TABLE,null,values);
    }

    public Cursor getAll(){
        String[] columns={"nr","artist","album","song"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,columns,null,null,null,null,null);
        return cursor;
    }

    public void deleteSong(int id){
        SQLiteDatabase database = getWritableDatabase();
        String[] args={""+id};
        database.delete(DATABASE_TABLE,"nr=?",args);
    }
}
