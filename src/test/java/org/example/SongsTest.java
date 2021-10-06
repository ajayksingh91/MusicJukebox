package org.example;

import org.example.impl.SongsImpl;
import org.example.model.Songs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;

public class SongsTest {

    SongsImpl songImpl;

    @BeforeEach
    public void setUp()
    {
        songImpl = new SongsImpl();
    }
    @AfterEach
    public void tearDown()
    {
        songImpl = null;
    }
    @Test
    void givenDataInsertSongIntoSongTable()
    {
        assertEquals(true,songImpl.insertSong(new Songs("s1009","loop",10.05,"a111",
                "F:\\Music\\Groove-machine-electronic-synth-loop.wav","al2314")));
    }
    @Test
    void givenSongIdTodeleteSongFromSOngTable()
    {
        assertEquals(false,songImpl.deleteSong("loop"));
        assertEquals(false,songImpl.deleteSong("hero"));
    }
}
