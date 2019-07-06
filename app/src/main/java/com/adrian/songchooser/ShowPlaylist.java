package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class ShowPlaylist extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_playlist);

        listView = (ListView)findViewById(R.id.playlist);

        final ArrayList<String> playlist = new ArrayList<>();

        ManageDatabase database = ManageDatabase.getInstance(this);
        for(Song song: database.getAllSongs()) {
            playlist.add(song.getNr()+". "+song.getArtistName()+" - "+song.getSongName());
        }

        adapter = new ArrayAdapter<>(this,R.layout.row_list_item,playlist);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String uri = "spotify:track:<spotify uri>";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(uri));
                startActivity(intent);
            }
        });
    }
}
