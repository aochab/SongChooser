package com.adrian.songchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddData extends AppCompatActivity {

    private TextInputLayout textInputArtist;
    private TextInputLayout textInputAlbum;
    private TextInputLayout textInputGenre;
    private TextInputLayout textInputSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        textInputArtist = findViewById(R.id.text_input_artist);
        textInputAlbum = findViewById(R.id.text_input_album);
        textInputGenre = findViewById(R.id.text_input_genre);
        textInputSong = findViewById(R.id.text_input_song);

    }

    private boolean validateArtist(){
        String artistInput = textInputArtist.getEditText().getText().toString();

        if(artistInput.isEmpty()){
            textInputArtist.setError("Field can't be empty");
            return false;
        }else{
            textInputArtist.setError(null);
            return true;
        }
    }

    private boolean validateAlbum(){
        String albumInput = textInputAlbum.getEditText().getText().toString();

        if(albumInput.isEmpty()){
            textInputAlbum.setError("Field can't be empty");
            return false;
        }else{
            textInputAlbum.setError(null);
            return true;
        }
    }

    private boolean validateGenre(){
        String artistInput = textInputGenre.getEditText().getText().toString();

        if(artistInput.isEmpty()){
            textInputGenre.setError("Field can't be empty");
            return false;
        }else{
            textInputGenre.setError(null);
            return true;
        }
    }

    private boolean validateSong(){
        String songInput = textInputSong.getEditText().getText().toString();

        if(songInput.isEmpty()){
            textInputSong.setError("Field can't be empty");
            return false;
        }else{
            textInputSong.setError(null);
            return true;
        }
    }

    public void confirmInput(View view){
        if( !validateArtist() | !validateAlbum() | !validateGenre() | !validateSong() ){
            return;
        }

        String input = "Artist: " + textInputArtist.getEditText().getText().toString() + "\n";
        input += "Album: " + textInputAlbum.getEditText().getText().toString() + "\n";
        input += "Song: " + textInputSong.getEditText().getText().toString() + "\n";
        input += "Genre: " + textInputGenre.getEditText().getText().toString() + "\n";

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
