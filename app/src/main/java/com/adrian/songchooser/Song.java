package com.adrian.songchooser;

public class Song {
    private int nr;
    private String artistName;
    private String albumName;
    private String songName;

    Song(){}

    Song(String artistName, String albumName, String songName){
        this.artistName = artistName;
        this.albumName = albumName;
        this.songName = songName;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

}
