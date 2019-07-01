package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mAddDataButton;
    private Button mShowPlaylistButton;
    private Button mRandomButton;

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

        mRandomButton = (Button)findViewById((R.id.random));
        mRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRandom();
            }
        });
    }

    private void openAddData() {
        Intent intent = new Intent(this, AddData.class);
        startActivity(intent);
    }

    private void openShowPlaylist() {
        Intent intent = new Intent(this, ShowPlaylist.class);
        startActivity(intent);
    }

    private void openRandom() {
        Intent intent = new Intent(this, Random.class);
        startActivity(intent);
    }
}


//androidx.constraintlayout.widget.ConstraintLayout