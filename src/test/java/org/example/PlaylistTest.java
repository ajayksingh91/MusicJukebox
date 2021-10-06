package org.example;

import org.example.impl.PlaylistImpl;
import org.example.model.Playlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest
{
    PlaylistImpl playlistImpl;

    @BeforeEach
    void setUp()
    {
        playlistImpl =new PlaylistImpl();
    }

    @AfterEach
    void tearDown()
    {
        playlistImpl=null;
    }

    @Test
    void givenDataToInsertPlayListIntoTable() {
        assertEquals(true,playlistImpl.insertPlaylist(new Playlist("play518","relax","2021-06-20")));
    }

    @Test
    void givenSongIdToDeletePlayList()
    {
        assertEquals(false,playlistImpl.deletePlaylist("play222"));
        assertEquals(false,playlistImpl.deletePlaylist("play7654"));

    }
    @Test
    void givenSongObjectToUpdatePlaList() {
        assertEquals(true,playlistImpl.updatePlaylist(new Playlist("play333","playlist3","2020-12-24")));
        assertEquals(false,playlistImpl.updatePlaylist(new Playlist("play6592","playlist76","2020-12-24")));
    }

}
