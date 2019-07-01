package com.adrian.songchooser;

import java.util.ArrayList;
import java.util.List;

public class Album {
    String genre;
    String albumName;
    List<Song> trackList;
    int numberOfSongs;

    Album(){
        this.albumName = "unknown";
        this.genre="unspecified";
        trackList = new ArrayList<>();
        this.numberOfSongs = trackList.size();
    }

    Album(String albumName, String genre){
        this.albumName = albumName;
        this.genre = genre;
        trackList = new ArrayList<>();
        this.numberOfSongs = trackList.size();
    }

    public void addSong(Song song){
        trackList.add(song);
    }

}
