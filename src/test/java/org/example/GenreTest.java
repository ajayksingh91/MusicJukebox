package org.example;

import org.example.impl.GenreImpl;
import org.example.model.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class GenreTest {

    GenreImpl genreImpl;
    @BeforeEach
    void setUp()
    {
        genreImpl = new GenreImpl();
    }
    @AfterEach
    void tearDown()
    {
        genreImpl = null;
    }
    @Test
    void givenDataToInsertGenreToTheGenreTable()
    {

        assertEquals(true,genreImpl.insertGenre(new Genre("g161","pop")));
    }
    @Test
    void givenGenrenameToSearchByGenreNameFromTable()
    {
        assertEquals("g111",genreImpl.searchByGenreName("pop").getGenreId());
        assertEquals(null,genreImpl.searchByGenreName("zazz"));
    }
    @Test
    void givenGenreIdDeleteGenreFromTable()
    {
        assertEquals(false,genreImpl.deleteGenre("g555"));
        assertEquals(false,genreImpl.deleteGenre("g2314"));
    }
    @Test
    void  givenGenreObjectToUpdateGenreNameInGenreTable()
    {
        assertEquals(true,genreImpl.updateGenre(new Genre( "g222","POP")));
        assertEquals(false,genreImpl.updateGenre(new Genre( "g1008","POP")));
    }
}
