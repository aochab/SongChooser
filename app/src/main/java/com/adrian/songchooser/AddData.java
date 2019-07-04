package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddData extends AppCompatActivity {

    private TextInputLayout textInputArtist;
    private TextInputLayout textInputAlbum;
    private TextInputLayout textInputSong;

    private String artistInput;
    private String albumInput;
    private String songInput;

    ManageDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        textInputArtist = findViewById(R.id.text_input_artist);
        textInputAlbum = findViewById(R.id.text_input_album);
        textInputSong = findViewById(R.id.text_input_song);

        database = ManageDatabase.getInstance(this);
    }

    public void confirmInput(View view){
        getData();
        if( !validateArtist() | !validateAlbum() | !validateSong() ){
            return;
        }

        String input = "Artist: " + artistInput + "\n";
        input += "Album: " + albumInput + "\n";
        input += "Song: " + songInput;

        Song song = new Song(artistInput,albumInput,songInput);
        if(addSong(song)){
            database.addSong(song);
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "This song is already added to playlist", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData(){
        artistInput = textInputArtist.getEditText().getText().toString();
        albumInput = textInputAlbum.getEditText().getText().toString();
        songInput = textInputSong.getEditText().getText().toString();
    }

    private boolean validateArtist(){
        if(artistInput.isEmpty()){
            textInputArtist.setError("Field can't be empty");
            return false;
        }else{
            textInputArtist.setError(null);
            return true;
        }
    }

    private boolean validateAlbum(){
        if(albumInput.isEmpty()){
            textInputAlbum.setError("Field can't be empty");
            return false;
        }else{
            textInputAlbum.setError(null);
            return true;
        }
    }

    private boolean validateSong(){
        if(songInput.isEmpty()){
            textInputSong.setError("Field can't be empty");
            return false;
        }else{
            textInputSong.setError(null);
            return true;
        }
    }

    private boolean addSong(Song song){
        for(Song s: database.getAllSongs()) {
            if(s.getArtistName().equalsIgnoreCase(song.getArtistName())){
                if(s.getAlbumName().equalsIgnoreCase(song.getAlbumName())){
                    if(s.getSongName().equalsIgnoreCase(song.getSongName())){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
