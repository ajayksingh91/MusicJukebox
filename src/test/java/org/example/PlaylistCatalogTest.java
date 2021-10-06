package org.example;

import org.example.impl.PlaylistCatalogImpl;
import org.example.model.PlaylistCatalog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaylistCatalogTest
{
    PlaylistCatalogImpl playlistCatalogImpl;

    @BeforeEach
    void setUp()
    {
        playlistCatalogImpl =new PlaylistCatalogImpl();
    }

    @AfterEach
    void teatDown()
    {
        playlistCatalogImpl=null;
    }

    @Test
    void givenDataToInsertItemToPlayList()
    {
        assertEquals(true,playlistCatalogImpl.insertItemToPlayListCatalog(new PlaylistCatalog("c007","play222","song","s222")));
        assertEquals(true,playlistCatalogImpl.insertItemToPlayListCatalog(new PlaylistCatalog("c8008","play444","podcast","play444")));
    }

    @Test
    void givenSongIdTodeleteSongFormPlaylist()
    {
        assertEquals(true,playlistCatalogImpl.deleteItemFromPlaylistCatalog("c007"));
        assertEquals(false,playlistCatalogImpl.deleteItemFromPlaylistCatalog("c000"));
    }
    @Test
    void givenPodcastIdTodeletePodcastFormPlaylist()
    {
        assertEquals(true,playlistCatalogImpl.deleteItemFromPlaylistCatalog("c8008"));
        assertEquals(false,playlistCatalogImpl.deleteItemFromPlaylistCatalog("c9999"));
    }
    @Test
    void givenItemUpdatePlaylistforSongOrPodcast()
    {
        assertEquals(true,playlistCatalogImpl.updatePlayListCatalog(new PlaylistCatalog("c007","play111","s333","song")));
        assertEquals(false,playlistCatalogImpl.updatePlayListCatalog(new PlaylistCatalog("c014","pl004","s361","podcast")));
    }
    @Test
    void givenListOfPlayListToFindSizeofPlaylist()
    {
        assertEquals(5,playlistCatalogImpl.getAllPlayListCatalog("play333").size());
        assertEquals(0,playlistCatalogImpl.getAllPlayListCatalog("play222").size());
    }
}
