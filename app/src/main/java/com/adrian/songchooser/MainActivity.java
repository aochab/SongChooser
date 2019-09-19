package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mAddDataButton;
    private Button mShowPlaylistButton;
    private Button mRandomButton;
    private Button mMediaPlayerButton;

    private MediaPlayerService player;
    boolean serviceBound = false;

    private String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddDataButton = (Button)findViewById(R.id.addSong);
        mAddDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddData();
            }
        });

        mShowPlaylistButton = (Button)findViewById(R.id.showPlaylist);
        mShowPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShowPlaylist();
            }
        });

        mRandomButton = (Button)findViewById(R.id.random);
        mRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRandom();
            }
        });
        playAudio("https://upload.wikimedia.org/wikipedia/commons/6/6c/Grieg_Lyric_Pieces_Kobold.ogg");
/*
        mMediaPlayerButton = (Button)findViewById(R.id.mediaPlayer);
        mMediaPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMediaPlayer();
            }
        });
*/
    }

    private void openAddData() {
        Intent intent = new Intent(this, AddData.class);
        intent.putExtra("artists", json);
        startActivity(intent);
    }

    private void openShowPlaylist() {
        Intent intent = new Intent(this, ShowPlaylist.class);
        //intent.putExtra("artists", json);
        startActivity(intent);
    }

    private void openRandom() {
        Intent intent = new Intent(this, RandomSong.class);
        intent.putExtra("artists", json);
        startActivity(intent);
    }
/*
    private void openMediaPlayer() {
        Intent intent = new Intent(this, MediaPlayer.class);
        intent.putExtra("artists", json);
        startActivity(intent);
    }
*/
    private void openDataBase(){
        SQLiteDatabase myDB = openOrCreateDatabase("my.db",MODE_PRIVATE,null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (artist String, Album String, Song String)");
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            player = binder.getService();
            serviceBound = true;

            Toast.makeText(MainActivity.this, "Service Bound", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name){
            serviceBound = false;
        }
    };

    private void playAudio(String media) {
        if(!serviceBound) {
            Intent playerIntent = new Intent(this, MediaPlayerService.class);
            playerIntent.putExtra("media",media);
            startService(playerIntent);
            bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else {
            //service is active
            //send media with BroadcastReceiver
        }
    }
}
