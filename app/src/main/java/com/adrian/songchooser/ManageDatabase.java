package com.adrian.songchooser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class ManageDatabase extends SQLiteOpenHelper {

    private static ManageDatabase sInstance;

    private static final String DATABASE_NAME = "playlist.db";
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

    public void addSong(Song song){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("artist",song.getArtistName());
        values.put("album",song.getAlbumName());
        values.put("song",song.getSongName());
        db.insertOrThrow(DATABASE_TABLE,null,values);
    }

    public Song getSong(int nr){
        Song playlist = new Song();
        SQLiteDatabase database = getWritableDatabase();
        String[] columns = {"nr","artist","album","song"};
        String[] args = {nr+""};
        Cursor cursor = database.query(DATABASE_TABLE,columns," nr=?",args,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            playlist.setNr(cursor.getInt(0));
            playlist.setArtistName(cursor.getString(1));
            playlist.setAlbumName(cursor.getString(2));
            playlist.setSongName(cursor.getString(3));
        }
        return playlist;
    }

    public List<Song> getAllSongs(){
        List<Song> songs = new LinkedList<>();
        String[] columns={"nr","artist","album","song"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,columns,null,null,null,null,null);
        while(cursor.moveToNext()) {
            Song song = new Song();
            song.setNr(cursor.getInt(0));
            song.setArtistName(cursor.getString(1));
            song.setAlbumName(cursor.getString(2));
            song.setSongName(cursor.getString(3));
            songs.add(song);
        }
        return songs;
    }

    public void deleteSong(int id){
        SQLiteDatabase database = getWritableDatabase();
        String[] args={""+id};
        database.delete(DATABASE_TABLE,"nr=?",args);
    }
}
