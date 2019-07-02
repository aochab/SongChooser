package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        playlist = (ListView)findViewById(R.id.playlist);

        String json = getIntent().getStringExtra("artists");
        artists = new Gson().fromJson(json,ArtistsList.class);
        ArrayList<String> a = new ArrayList<>();
        a.addAll(Arrays.asList(artists.artistList.toString()));

        adapter=new ArrayAdapter<String>(this,R.layout.row_in_playlist,a);
        playlist.setAdapter(adapter);
    }
}
