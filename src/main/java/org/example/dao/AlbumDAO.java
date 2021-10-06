package org.example.dao;

import org.example.model.Album;
import org.example.userdefinedexception.NoSuchAlbumAvaiableException;

import java.util.List;

public interface AlbumDAO {

    List<Album> viewAllAlbums();
    void display(List<Album> albums);
    Album searchByAlbumName(String albumName) throws NoSuchAlbumAvaiableException;
    boolean insertAlbum(Album album);
    boolean updateAlbum(Album album);
    boolean deleteAlbum(String albumName);
    Album getAlbumById(String albumId);

}
