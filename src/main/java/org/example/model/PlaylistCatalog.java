package org.example.model;

public class PlaylistCatalog {

    private String playlistDetailsId;
    private String itemId;
    private String type;
    private String playlistId;

    public PlaylistCatalog(String playlistDetailsId, String itemId, String type, String playlistId) {
        this.playlistDetailsId = playlistDetailsId;
        this.itemId = itemId;
        this.type = type;
        this.playlistId = playlistId;
    }

    public String getPlaylistDetailsId() {
        return playlistDetailsId;
    }

    public void setPlaylistDetailsId(String playlistDetailsId) {
        this.playlistDetailsId = playlistDetailsId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
