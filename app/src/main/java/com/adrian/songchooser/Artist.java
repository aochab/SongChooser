package com.adrian.songchooser;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    String artistName;
    List<Album> albumList;

    Artist(){
        this.artistName = "No Artist";
        albumList = new ArrayList<>();
    }

    Artist(String artistName){
        this.artistName = artistName;
        albumList = new ArrayList<>();
    }

    public void addAlbum(Album album){
        albumList.add(album);
    }

}

