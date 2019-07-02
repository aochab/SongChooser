package com.adrian.songchooser;

import java.util.ArrayList;
import java.util.List;

public class ArtistsList {
    List<Artist> artistList;

    ArtistsList(){
        artistList = new ArrayList<>();
    }

    public void addArtist(Artist artist){
        artistList.add(artist);
    }

    public boolean isEmpty(){
        return artistList.isEmpty();
    }
}
