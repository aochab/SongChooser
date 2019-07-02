package com.adrian.songchooser;

import java.util.ArrayList;
import java.util.List;

public class Album {
    public String albumName;
    List<Song> trackList;
    int numberOfSongs;

    Album(){
        this.albumName = "unknown";
        trackList = new ArrayList<>();
        this.numberOfSongs = trackList.size();
    }

    Album(String albumName){
        this.albumName = albumName;
        trackList = new ArrayList<>();
        this.numberOfSongs = trackList.size();
    }

    public void addSong(Song song){
        trackList.add(song);
    }

}
