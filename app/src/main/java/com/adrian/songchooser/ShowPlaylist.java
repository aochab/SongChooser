package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;



public class ShowPlaylist extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_playlist);

        listView = (ListView)findViewById(R.id.playlist);

        ArrayList<String> playlist = new ArrayList<>();

        ManageDatabase database = ManageDatabase.getInstance(this);
        for(Song song: database.getAllSongs()) {
            playlist.add(song.getNr()+". "+song.getArtistName()+" - "+song.getSongName());
        }

        adapter = new ArrayAdapter<>(this,R.layout.row_list_item,playlist);
        listView.setAdapter(adapter);

    }
}
