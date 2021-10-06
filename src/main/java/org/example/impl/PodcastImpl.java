package org.example.impl;

import org.example.dao.PodcastDAO;
import org.example.helper.MySqlConnection;
import org.example.model.Podcast;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PodcastImpl implements PodcastDAO {

    private Connection connection;

    public PodcastImpl() {
        connection = MySqlConnection.getConnection();
    }

// view all the podcast from the data base
    @Override
    public List<Podcast> viewAllPodcast() {

        List<Podcast> podcasts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from podcast");
            while (resultSet.next())
            {
                Podcast podcast = new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getString("date_of_publish"),
                        resultSet.getString("artist_id"),resultSet.getString("location"));
                podcasts.add(podcast);
            }
            return podcasts;
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

    // get the podcast name by celebrity name from data base
    @Override
    public Podcast getPodcastByName(String celebrityName) {

        try
        {
            String query = "select * from podcast where podcast_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,celebrityName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Podcast podcast = new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getString("date_of_publish"),
                        resultSet.getString("artist_id"),resultSet.getString("location"));
                return podcast;
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

    // get all podcast by date
    @Override
    public Podcast getPodcastByDate(String date) {
        try
        {
            String query = "select * from podcast where date_of_publish = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Podcast podcast = new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getString("date_of_publish"),
                        resultSet.getString("artist_id"),resultSet.getString("location"));
                return podcast;
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

    //insert podcast into the podcast in data base
    @Override
    public boolean insertPodcast(Podcast podcast) {

        try
        {
            String query = "insert into podcast(podcast_id, podcast_name, duration, date_of_publish,artist_id,location) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,podcast.getPodcastId());
            preparedStatement.setString(2,podcast.getPodcastName());
            preparedStatement.setDouble(3,podcast.getDuration());
            preparedStatement.setString(4,podcast.getDateOfPublish());
            preparedStatement.setString(5,podcast.getArtistId());
            preparedStatement.setString(6,podcast.getLocation());

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

    //delete podcast from data base by name
    @Override
    public boolean deletePodcast(String name) {

        try
        {
            String query = "delete from podcast where podcast_name = ?";
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

    /*@Override
    public void displayPodcast(List<Podcast> podcasts) {

        System.out.format("%-10s%-20s%-20s%-20s%-20s%-20s","Podcast id","PodcastName","Duration","Date Of Publish","Artist Id","Location\n");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        for(Podcast podcast : podcasts)
        {
            System.out.format("\n%-10s%-20s%-20s%-20s%-20s%-20s",podcast.getPodcastId(),podcast.getPodcastName()
                    ,podcast.getDuration(), podcast.getDateOfPublish(),podcast.getArtistId(),podcast.getLocation());
        }
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
    }*/

    //**************** Java 8 for Display ****************
    @Override
    public void displayPodcast(List<Podcast> podcasts)
    {
        System.out.format("%-10s%-20s%-20s%-20s%-20s%-20s","Podcast id","PodcastName","Duration","Date Of Publish","Artist Id","Location\n");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        podcasts.forEach(podcast ->System.out.format("\n%-10s%-20s%-20s%-20s%-20s%-20s",podcast.getPodcastId(),
                podcast.getPodcastName(),podcast.getDuration(),podcast.getDateOfPublish(),podcast.getArtistId()
                ,podcast.getLocation()));
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");

    }

// search podcast by the podcast name
    @Override
    public List<Podcast> searchByPodcastName(String podcastName) {

        List<Podcast> podcastList= new ArrayList<>();
        try
        {
            String query = "select * from podcast where podcast_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,podcastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Podcast podcast = new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getString("date_of_publish"),
                        resultSet.getString("artist_id"),resultSet.getString("location"));
                podcastList.add(podcast);
            }
            return podcastList;
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

    // search by date of publish of podcast
    @Override
    public List<Podcast> searchByDateOfPublish(Date date) {

        List<Podcast> podcastList= new ArrayList<>();
        try
        {
            String query = "select * from podcast where date_of_publish = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            java.sql.Date dateOfPublish= new java.sql.Date(date.getTime());
            preparedStatement.setDate(1,dateOfPublish);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Podcast podcast = new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getString("date_of_publish"),
                        resultSet.getString("artist_id"),resultSet.getString("location"));
                podcastList.add(podcast);
            }
            return podcastList;
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

    // search by artist using artist id from data base
    @Override
    public List<Podcast> searchByArtist(String artistId) {
        List<Podcast> podcastList= new ArrayList<>();
        try
        {
            String query = "select * from podcast where artist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getString("date_of_publish"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location"));
                podcastList.add(podcast);
            }
            return podcastList;
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
