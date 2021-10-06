package org.example.impl;

import org.example.dao.GenreDAO;
import org.example.helper.MySqlConnection;
import org.example.model.Genre;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreImpl implements GenreDAO {

    private Connection connection;

    public GenreImpl() {
        connection = MySqlConnection.getConnection();
    }

    // view all genre from data base
    @Override
    public List<Genre> viewAllGenres() {

        List<Genre> genres = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from genre");
            while (resultSet.next())
            {
                Genre genre = new Genre(resultSet.getString("genre_id"),resultSet.getString("genre_name"));
                genres.add(genre);
            }
            return genres;
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

    // search by the genre name
    @Override
    public Genre searchByGenreId(String genreId) {

        try
        {
            String query = "select * from genre where genre_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genreId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Genre genre = new Genre(resultSet.getString("genre_id"),resultSet.getString("genre_name"));
                return genre;
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
    public Genre searchByGenreName(String genreName) {

        try
        {
            String query = "select * from genre where genre_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genreName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Genre genre = new Genre(resultSet.getString("genre_id"),resultSet.getString("genre_name"));
                return genre;
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
    // insert into the genre into data base

    @Override
    public boolean insertGenre(Genre genre) {

        try
        {
            String query = "insert into genre(genre_id, genre_name) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genre.getGenreId());
            preparedStatement.setString(2,genre.getGenreName());

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

    /*@Override
    public void displayGenre(List<Genre> genres) {

        for(Genre genre : genres)
        {
            System.out.println(" Genre ID : "+ genre.getGenreId()+" Genre Name : "+genre.getGenreName());
        }
    }*/

    //**************** Java 8 for Display ****************
    @Override
    public void displayGenre(List<Genre> genres)
    {
        System.out.format("%-10s%-20s","Genre ID","GenreName\n");
        System.out.println("\n------------------------------------------------------");
        genres.forEach(genre ->System.out.format("\n%-10s%-20s",genre.getGenreId(),genre.getGenreName()));
        System.out.println("\n--------------------------------------------------------");
    }

    //delete genre from data base by name
    @Override
    public boolean deleteGenre(String genreName) {

        try
        {
            String query = "delete from genre where genre_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genreName);
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

    // update the genre in the data base
    @Override
    public boolean updateGenre(Genre genre) {

        try
        {
            String query = "update genre set genre_name = ? where genre_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genre.getGenreName());
            preparedStatement.setString(2,genre.getGenreId());

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
}
