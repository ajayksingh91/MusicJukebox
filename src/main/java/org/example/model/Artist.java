package org.example.model;

public class Artist {

    private String artistId;
    private String artistName;
    private String genreId;

    public Artist(String artistId, String artistName, String genreId) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.genreId = genreId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }
}
