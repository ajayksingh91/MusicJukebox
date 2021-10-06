package org.example.model;

public class Songs {

    private String songId;
    private String songName;
    private Double duration;
    private String artistId;
    private String albumId;
    private String location;

    public Songs(String songId, String songName, Double duration, String artistId, String location, String albumId) {
        this.songId = songId;
        this.songName = songName;
        this.duration = duration;
        this.artistId = artistId;
        this.location = location;
        this.albumId = albumId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
