package org.example.impl;

import org.example.dao.SongDAO;
import org.example.helper.MySqlConnection;
import org.example.model.Artist;
import org.example.model.Songs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongsImpl implements SongDAO {

    private Connection connection;

    public SongsImpl() {
        connection = MySqlConnection.getConnection();
    }

    //view all songs from data base
    @Override
    public List<Songs> viewAllSongs() {

        List<Songs> songs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from songs");
            while (resultSet.next()) {
                Songs songs1 = new Songs(resultSet.getString("song_id"), resultSet.getString("song_name"),
                        resultSet.getDouble("duration"), resultSet.getString("artist_id"),
                        resultSet.getString("location"), resultSet.getString("album_id"));
                songs.add(songs1);
            }
            return songs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /*@Override
    public void displaySongs(List<Songs> songs) {

        System.out.format("%-10s%-30s%-20s%-20s%-20s%-20s","Song id","SongName","Duration","Album Id","Artist Id","Location\n");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        for(Songs songs1 : songs)
        {
            System.out.format("\n%-10s%-30s%-20s%-20s%-20s%-20s",songs1.getSongId(),songs1.getSongName(),songs1.getDuration(),
                    songs1.getAlbumId(),songs1.getArtistId(),songs1.getLocation());
        }
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");

    }*/

    //**************** Java 8 for Display ****************
    @Override
    public void displaySongs(List<Songs> songs) {
        System.out.format("%-10s%-30s%-20s%-20s%-20s%-20s", "Song id", "SongName", "Duration", "Album Id", "Artist Id", "Location\n");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        songs.forEach(song -> System.out.format("\n%-10s%-30s%-20s%-20s%-20s%-20s", song.getSongId(), song.getSongName(),
                song.getDuration(), song.getAlbumId(), song.getArtistId(), song.getLocation()));
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //insert new song into the data base
    @Override
    public boolean insertSong(Songs song) {

        try {
            String query = "insert into songs(song_id, song_name, duration, artist_id,location,album_id) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, song.getSongId());
            preparedStatement.setString(2, song.getSongName());
            preparedStatement.setDouble(3, song.getDuration());
            preparedStatement.setString(4, song.getArtistId());
            preparedStatement.setString(5, song.getLocation());
            preparedStatement.setString(6, song.getAlbumId());

            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //search song by song name
    @Override
    public Songs searchSongBySongName(String songName) {
        try {
            String query = "select * from songs where song_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, songName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Songs songs = new Songs(resultSet.getString("song_id"), resultSet.getString("song_name"),
                        resultSet.getDouble("duration"), resultSet.getString("artist_id"),
                        resultSet.getString("location"), resultSet.getString("album_id"));
                return songs;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // search song by artist name
    @Override
    public Artist searchArtistBySongName(String songName) {

        try {
            String query = "select * from artist inner join songs on songs.artist_id = artist.artist_id where songs.song_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, songName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Artist artist = new Artist(resultSet.getString("artist_id"), resultSet.getString("artist_name"),
                        resultSet.getString("genre_id"));
                return artist;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    //delete song from data base by song name
    @Override
    public boolean deleteSong(String songName) {

        try {
            String query = "delete from songs where song_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, songName);
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //get all the song by there ID from data base
    @Override
    public Songs getSongByID(String songId) {
        try {
            String query = "select * from songs where song_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, songId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Songs songs = new Songs(resultSet.getString("song_id"), resultSet.getString("song_name"),
                        resultSet.getDouble("duration"), resultSet.getString("artist_id"),
                        resultSet.getString("location"), resultSet.getString("album_id"));
                return songs;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //search song by album name
    @Override
    public List<Songs> searchSongByAlbum(String albumName) {
        List<Songs> songsList = new ArrayList<>();
        try {
            String query = "select * from songs where album_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, albumName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Songs song = new Songs(resultSet.getString("song_id"), resultSet.getString("song_name"),
                        resultSet.getDouble("duration"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location")
                        , resultSet.getString("album_id"));
                songsList.add(song);
            }
            return songsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //search song by artist ID from data base
    @Override
    public List<Songs> searchSongByArtist(String artistId) {

        List<Songs> songsList = new ArrayList<>();
        try {
            String query = "select * from songs where artist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Songs song = new Songs(resultSet.getString("song_id"), resultSet.getString("song_name"),
                        resultSet.getDouble("duration"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location")
                        , resultSet.getString("album_id"));
                songsList.add(song);
            }
            return songsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
