package org.example.dao;

import org.example.model.Playlist;

import java.util.List;

public interface PlaylistDAO {

    List<Playlist> getAllPlaylist();
    void displayPlayList(List<Playlist> playlists);
    boolean updatePlaylist(Playlist playlist);
    boolean insertPlaylist(Playlist playlist);
    boolean deletePlaylist(String playlist_id);
    Playlist getPlayList(String playListName);
}
