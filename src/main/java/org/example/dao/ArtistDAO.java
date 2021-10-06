package org.example.dao;

import org.example.model.Artist;

import java.util.List;

public interface ArtistDAO {

    List<Artist> getAllArtist();
    Artist searchByArtistName(String name);
    Artist searchByGenre(String genre);
    boolean deleteArtist(String name);
    boolean insertArtist(Artist artist);
    boolean updateArtist(Artist artist);
    void displayArtist(List<Artist> artists);

}
