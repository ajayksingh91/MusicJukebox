package org.example.impl;

import org.example.dao.ArtistDAO;
import org.example.helper.MySqlConnection;
import org.example.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistImpl implements ArtistDAO {

    private Connection connection;

    public ArtistImpl() {
        connection = MySqlConnection.getConnection();
    }

    // show all artist from data base
    @Override
    public List<Artist> getAllArtist() {

        List<Artist> artists = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from artist");
            while (resultSet.next())
            {
                Artist artist = new Artist(resultSet.getString("artist_id"),resultSet.getString("artist_name"),
                                resultSet.getString("genre_id"));
                artists.add(artist);
            }
            return artists;
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

    // search by artist name
    @Override
    public Artist searchByArtistName(String name) {
        try
        {
            String query = "select * from artist where artist_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Artist artist = new Artist(resultSet.getString("artist_id"),resultSet.getString("artist_name"),
                        resultSet.getString("genre_id"));
                return artist;
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

    // search by genre name
    @Override
    public Artist searchByGenre(String genre) {
        try
        {
            String query = "select * from artist where genre_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genre);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Artist artist = new Artist(resultSet.getString("artist_id"),resultSet.getString("artist_name"),
                        resultSet.getString("genre_id"));
                return artist;
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

    // delete artist by artist name
    @Override
    public boolean deleteArtist(String name) {
        try
        {
            String query = "delete from artist where artist_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
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

    //insert into artist with details
    @Override
    public boolean insertArtist(Artist artist) {

        try
        {
            String query= "insert into artist(artist_id,artist_name,genre_id) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,artist.getArtistId());
            preparedStatement.setString(2,artist.getArtistName());
            preparedStatement.setString(3,artist.getGenreId());

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

    // update the artist in data base
    @Override
    public boolean updateArtist(Artist artist) {

        try
        {
            String query = "update artist set genre_id = ? where artist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,artist.getGenreId());
            preparedStatement.setString(2,artist.getArtistId());
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


   /* @Override
    public void displayArtist(List<Artist> artists) {
        for(Artist artist : artists)
        {
            System.out.println(" Artist ID : "+ artist.getArtistId()+" Artist Name : "+artist.getArtistName()+
                    " Genre ID : "+ artist.getGenreId());

        }
    }*/

   //**************** Java 8 for Display ****************
    @Override
    public void displayArtist(List<Artist> artists)
    {
        System.out.format("%-10s%-20s%-20s","Artist ID","ArtistName","Genre ID\n");
        System.out.println("\n------------------------------------------------------");
        artists.forEach(artist ->System.out.format("\n%-10s%-20s%-20s",artist.getArtistId(),artist.getArtistName(),
                artist.getGenreId()));
        System.out.println("\n--------------------------------------------------------");

    }

}
