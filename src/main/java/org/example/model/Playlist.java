package org.example.model;

public class Playlist {

    private String playlistId;
    private String playlistName;
    private String creationDate;

    public Playlist(String playlistId, String playlistName, String creationDate) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.creationDate = creationDate;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
