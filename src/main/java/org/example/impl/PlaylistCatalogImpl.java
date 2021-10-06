package org.example.impl;

import org.example.dao.PlaylistCatalogDAO;
import org.example.helper.MySqlConnection;
import org.example.model.PlaylistCatalog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistCatalogImpl implements PlaylistCatalogDAO {

    private Connection connection;

    public PlaylistCatalogImpl() {
        connection = MySqlConnection.getConnection();
    }

    // show all the catalog present in the data base
    @Override
    public List<PlaylistCatalog> getAllPlayListCatalog(String playlistId) {

        List<PlaylistCatalog> playlistCatalogArrayList =new ArrayList<>();

        try
        {
            String query="select * from playlist_catalog where playlist_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                PlaylistCatalog playlistCatalog= new PlaylistCatalog(resultSet.getString("catalog_id"), resultSet.getString("item_id"),
                        resultSet.getString("type"),
                        resultSet.getString("playlist_id"));
                playlistCatalogArrayList.add(playlistCatalog);
            }
            return playlistCatalogArrayList;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

   /* @Override
    public void displayPlaylistCatalog(List<PlaylistCatalog> playlistCatalogs) {

        for(PlaylistCatalog playlistCatalog : playlistCatalogs)
        {
            System.out.println(" Catalog ID : "+playlistCatalog.getPlaylistDetailsId()+" Item ID : "+playlistCatalog.getItemId()+
                    " Type : "+playlistCatalog.getType()+" Playlist ID : "+playlistCatalog.getItemId());
        }

    }*/

//**************** Java 8 for Display ****************
    @Override
    public void displayPlaylistCatalog(List<PlaylistCatalog> playlistCatalogs)
    {
        System.out.format("%-10s%-30s%-20s%-20s","Catalog ID","Item ID","Type","Playlist ID\n");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        playlistCatalogs.forEach(catalog ->System.out.format("\n%-10s%-30s%-20s%-20s",catalog.getPlaylistDetailsId(),
                catalog.getItemId(),catalog.getType(),catalog.getPlaylistId()));
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //insert item into the playlist catalog into data base
    @Override
    public boolean insertItemToPlayListCatalog(PlaylistCatalog playlistCatalog) {

        try
        {
            String query= "insert into playlist_catalog(catalog_id,item_id,type,playlist_id) values(?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,playlistCatalog.getPlaylistDetailsId());
            preparedStatement.setString(2,playlistCatalog.getItemId());
            preparedStatement.setString(3,playlistCatalog.getType());
            preparedStatement.setString(4, playlistCatalog.getPlaylistId());
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

    // delete item from database by the item id
    @Override
    public boolean deleteItemFromPlaylistCatalog(String itemId) {

        try
        {
            String query = "delete from playlist_catalog where catalog_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,itemId);
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

    //update the catalog by entering the details
    @Override
    public boolean updatePlayListCatalog(PlaylistCatalog playlistCatalog) {

        try
        {
            String query = "update playlist_catalog set type = ? where catalog_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playlistCatalog.getType());
            preparedStatement.setString(2, playlistCatalog.getPlaylistDetailsId());
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
