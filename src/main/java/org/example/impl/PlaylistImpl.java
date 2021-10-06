package org.example.impl;

import org.example.dao.PlaylistDAO;
import org.example.helper.MySqlConnection;
import org.example.model.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistImpl implements PlaylistDAO {

    private Connection connection;

    public PlaylistImpl() {
        connection = MySqlConnection.getConnection();
    }

    // get all the playlist present from the data base
    @Override
    public List<Playlist> getAllPlaylist() {

        List<Playlist> playlists = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from playlist");
            while (resultSet.next())
            {
                Playlist playlist = new Playlist(resultSet.getString("playlist_id"),resultSet.getString("playlist_name"),
                        resultSet.getString("creation_date"));
                playlists.add(playlist);
            }
            return playlists;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    /*@Override
    public void displayPlayList(List<Playlist> playlists) {
        System.out.format("%-10s%-20s%-20s","Playlist id","Playlist Name","Creation Date\n");
        System.out.println("\n-----------------------------------------------------------------");
        for(Playlist playlist : playlists)
        {
            System.out.format("\n%-10s%-20s%-20s",playlist.getPlaylistId(),playlist.getPlaylistName()
                    ,playlist.getCreationDate());
        }
        System.out.println("\n------------------------------------------------------------------");

    }*/

    //**************** Java 8 for Display ****************

    @Override
    public void displayPlayList(List<Playlist> playlists)
    {
        System.out.format("%-10s%-20s%-20s","Playlist ID","PlaylistName","Creation Date\n");
        System.out.println("\n------------------------------------------------------");
        playlists.forEach(playlist ->System.out.format("\n%-10s%-20s%-20s",playlist.getPlaylistId(),
                playlist.getPlaylistName(),playlist.getCreationDate()));
        System.out.println("\n--------------------------------------------------------");
    }

    // update the playlist into the data base
    @Override
    public boolean updatePlaylist(Playlist playlist) {

        try
        {
            String query = "update playlist set creation_date = ? where playlist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playlist.getCreationDate());
            preparedStatement.setString(2,playlist.getPlaylistId());

            int count = preparedStatement.executeUpdate();
            if(count>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    // insert into the data base
    @Override
    public boolean insertPlaylist(Playlist playlist) {

        try
        {
            String query= "insert into playlist(playlist_id,playlist_name,creation_date) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,playlist.getPlaylistId());
            preparedStatement.setString(2,playlist.getPlaylistName());
            preparedStatement.setString(3, playlist.getCreationDate());

            int count=preparedStatement.executeUpdate();

            if(count>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    // delete the playlist from the data base
    @Override
    public boolean deletePlaylist(String playlist_id) {

        try
        {
            String query = "delete from playlist where playlist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playlist_id);
            int count = preparedStatement.executeUpdate();
            if(count>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    // get playlist by the playlist name
    @Override
    public Playlist getPlayList(String playlistName) {

        try
        {
            String query = "select * from playlist where playlist_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playlistName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Playlist playlist = new Playlist(resultSet.getString("playlist_id"),resultSet.getString("playlist_name"),
                        resultSet.getString("creation_date"));
                return playlist;
            }
            else
            {
                return null;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
