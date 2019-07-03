package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowPlaylist extends AppCompatActivity {

    public ArtistsList artists;
    public Artist artist;
    public Album album;
    public Song song;

    private ListView playlist;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_playlist);

        TextView playlist = (TextView)findViewById(R.id.playlist1);

        ManageDatabase database = ManageDatabase.getInstance(this);
        Cursor c = database.getAll();
        while(c.moveToNext()){
            int nr = c.getInt(0);
            String artist = c.getString(1);
            String album = c.getString(2);
            String song = c.getString(3);
            playlist.setText(playlist.getText()+"\n "+nr+" "+artist+" "+album+" "+song);
        }
    }
}
