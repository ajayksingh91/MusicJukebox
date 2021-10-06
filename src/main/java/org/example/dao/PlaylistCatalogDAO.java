package org.example.dao;

import org.example.model.PlaylistCatalog;

import java.util.List;

public interface PlaylistCatalogDAO {

    List<PlaylistCatalog> getAllPlayListCatalog(String playlistName);
    void displayPlaylistCatalog(List<PlaylistCatalog> playlistCatalogs);
    boolean insertItemToPlayListCatalog(PlaylistCatalog playlistCatalog);
    boolean deleteItemFromPlaylistCatalog(String itemId);
    boolean updatePlayListCatalog(PlaylistCatalog playlistCatalog);
}
