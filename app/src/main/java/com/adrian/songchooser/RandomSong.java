package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class RandomSong extends AppCompatActivity {

    TextView drawnSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_song);

        drawnSong = (TextView)findViewById(R.id.drawnSong);
    }

    public void drawSong(View view){
        ManageDatabase database = ManageDatabase.getInstance(this);
        Song song = null;
        int songsNumber = database.getAllSongs().size();

        if(songsNumber == 0) {
            drawnSong.setText("No songs");
            return;
        }

        Random r = new Random();
        int randomInt = r.nextInt(songsNumber)+1;
        for(int i=1; i<=songsNumber; i++){
            if(randomInt == i){
                song = database.getSong(i);
                break;
            }
        }

        if(song != null) {
            drawnSong.setText("Artist: " + song.getArtistName() + "\n" +
                    "Album: " + song.getAlbumName() + "\n" +
                    "Song: " + song.getSongName());
        }
        else{
            drawnSong.setText("No songs");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}


