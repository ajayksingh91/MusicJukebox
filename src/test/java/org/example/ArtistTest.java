package org.example;

import org.example.helper.MySqlConnection;
import org.example.impl.ArtistImpl;
import org.example.model.Artist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class ArtistTest {

    ArtistImpl artistImpl;
    private Connection connection;
    @BeforeEach
    public void initialize()
    {
        connection= MySqlConnection.getConnection();
    }
    @BeforeEach
    void setUp()
    {
        artistImpl = new ArtistImpl();
    }
    @AfterEach
    void tearDown()
    {
        artistImpl = null;
    }
    @Test
    void givenInputToInsertIntoArtistTable()
    {
        assertEquals(true, artistImpl.insertArtist(new Artist("a111","Arijit","g111")));
    }
    @Test
    void givenArtistNameToSearchArtistByArtistName()
    {
        assertEquals("Arijit",artistImpl.searchByArtistName("Arijit").getArtistName());
        assertEquals(null,artistImpl.searchByArtistName("Badshah"));
    }
    @Test
    void givenGenreIdTofindSizeOfSearchArtistByGenre()
    {
        assertEquals(3,artistImpl.searchByGenre("g111"));
        assertEquals(0,artistImpl.searchByGenre("g717"));
    }
    @Test
    void givenArtistObjectToUpdateArtistByName()
    {
        assertEquals(true,artistImpl.updateArtist(new Artist("a111","Arijit","g111")));
        assertEquals(false,artistImpl.updateArtist(new Artist("a009","Badshah","g309")));
    }
    @Test
    void givenArtistNameToDeleteArtistFromTheTable()
    {
        assertEquals(true,artistImpl.deleteArtist("Arijit"));
        assertEquals(false,artistImpl.deleteArtist("Arman Malik"));

    }
}
