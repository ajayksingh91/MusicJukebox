package org.example.dao;

import org.example.model.Artist;
import org.example.model.Songs;

import java.util.List;

public interface SongDAO {

    List<Songs> viewAllSongs();

    void displaySongs(List<Songs> songs);

    boolean insertSong(Songs song);

    Songs searchSongBySongName(String songName);

    Artist searchArtistBySongName(String songName);

    //List<Songs> searchSongByGenre(String genreName);

    boolean deleteSong(String songName);

    Songs getSongByID(String songId);

    List<Songs> searchSongByAlbum(String albumName);
    List<Songs> searchSongByArtist(String artistName);
}
