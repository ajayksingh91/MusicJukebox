package org.example.impl;

import org.example.dao.AlbumDAO;
import org.example.helper.MySqlConnection;
import org.example.model.Album;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumImpl implements AlbumDAO {

    private Connection connection;

    public AlbumImpl() {
        connection = MySqlConnection.getConnection();
    }


    // Showing All Albums
    @Override
    public List<Album> viewAllAlbums() {

        List<Album> albums = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from album");
            while (resultSet.next())
            {
                Album album = new Album(resultSet.getString("album_id"),resultSet.getString("album_name"),
                        resultSet.getString("release_date"));
                albums.add(album);
            }
            return albums;
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
    public void display(List<Album> albums) {
        for(Album album : albums)
        {
            System.out.println(" Album ID : "+album.getAlbumId()+" Album Name : "+album.getAlbumName()+
                    " Release Date : "+album.getReleaseDate());
        }

    }*/

    //**************** Java 8 for Display ****************

    @Override
    public void display(List<Album> albums)
    {

        System.out.format("%-10s%-20s%-20s","Album ID","Album Name","Release Date");
        albums.stream().forEach(album -> System.out.format("%-10s%-20s%-20s",album.getAlbumId(),
                album.getAlbumName(),album.getReleaseDate()));
    }

    //Search by album name
    @Override
    public Album searchByAlbumName(String albumName)
    {

        try
        {
            String query = "select * from album where album_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,albumName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Album album = new Album(resultSet.getString("album_id"),resultSet.getString("album_name"),
                        resultSet.getString("release_date"));
                return album;

            }
            else
            {
                return null;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    // insert into album
    @Override
    public boolean insertAlbum(Album album) {

        try
        {
            String query= "insert into album(album_id,album_name,release_date) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,album.getAlbumId());
            preparedStatement.setString(2,album.getAlbumName());
            preparedStatement.setString(3,album.getReleaseDate());

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

    //update the album
    @Override
    public boolean updateAlbum(Album album) {

        try
        {
            String query = "update album set release_date = ? where album_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,album.getReleaseDate());
            preparedStatement.setString(2,album.getAlbumId());
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

    // delete from album by album name
    @Override
    public boolean deleteAlbum(String albumName) {

        try
        {
            String query = "delete from album where album_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,albumName);
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

    // get album by album id
    @Override
    public Album getAlbumById(String albumId) {

        try
        {
            String query = "select * from album where album_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,albumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Album album = new Album(resultSet.getString("album_id"),resultSet.getString("album_name"),
                        resultSet.getString("release_date"));
                return album;
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
