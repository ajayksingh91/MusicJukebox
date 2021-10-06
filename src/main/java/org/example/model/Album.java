package org.example.model;

public class Album {

    private String albumId;
    private String albumName;
    private String releaseDate;

    public Album(String albumId, String albumName, String releaseDate) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.releaseDate = releaseDate;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
