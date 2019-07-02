package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AddData extends AppCompatActivity {

    private TextInputLayout textInputArtist;
    private TextInputLayout textInputAlbum;
    private TextInputLayout textInputSong;

    private String artistInput;
    private String albumInput;
    private String songInput;

    public ArtistsList artists;
    public Artist artist;
    public Album album;
    public Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        textInputArtist = findViewById(R.id.text_input_artist);
        textInputAlbum = findViewById(R.id.text_input_album);
        textInputSong = findViewById(R.id.text_input_song);

        String json = getIntent().getStringExtra("artists");
        artists = new Gson().fromJson(json,ArtistsList.class);

    }

    public void confirmInput(View view){
        getData();
        if( !validateArtist() | !validateAlbum() | !validateSong() ){
            return;
        }

        String input = "Artist: " + artistInput + "\n";
        input += "Album: " + albumInput + "\n";
        input += "Song: " + songInput;

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        addSongToList(artistInput,albumInput,songInput);
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

    private void addSongToList(String artistInput,String albumInput,String songInput){
        for(Artist a: artists.artistList){
            if(a.artistName.equals(artistInput)){
                artist = a;
                break;
            }
        }
        if(artist == null){
            artist = new Artist(artistInput);
            artists.addArtist(artist);
        }

        for(Album a: artist.albumList){
            if(a.albumName.equals(albumInput)){
                album = a;
                break;
            }
        }
        if(album == null){
            album = new Album(albumInput);
            artist.addAlbum(album);
        }

        for(Song s: album.trackList){
            if(s.title.equals(songInput)){
                song = s;
                break;
            }
        }
        if(song == null){
            song = new Song(songInput);
            album.addSong(song);
        }
    }
}
