package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class ShowPlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_playlist);

        TextView playlist = (TextView)findViewById(R.id.playlist);

        ManageDatabase database = ManageDatabase.getInstance(this);

        for(Song song: database.getAllSongs()) {
            playlist.setText(playlist.getText() + "\n" + song.getNr() + " " + song.getArtistName() + " " + song.getAlbumName() + " " + song.getSongName());
        }

    }
}
