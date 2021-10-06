package org.example;

import org.example.impl.AlbumImpl;
import org.example.model.Album;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;


import static org.junit.Assert.assertEquals;

public class AlbumTest {

    AlbumImpl albumImpl;

    @BeforeEach
    void setUp()
    {
        albumImpl = new AlbumImpl();
    }

    @AfterEach
    void tearDown()
    {
        albumImpl=null;
    }
    @Test
    void givenDataToInsertIntoAlbumTable() throws ParseException {
        assertEquals(true,albumImpl.insertAlbum(new Album("al2314","album1","2020-10-23")));
    }
    @Test
    void givenAlbumObjectToUpdateAlbumNameByAlbumId() throws ParseException {

        assertEquals(true,albumImpl.updateAlbum(new Album("al2314","album1","2020-10-23")));
        assertEquals(false,albumImpl.updateAlbum(new Album("al1000","album234","2025-10-23")));

    }
    @Test
    void givenAlbumIdToDeleteAlbumByAlbumId()
    {
        assertEquals(true,albumImpl.deleteAlbum("album1"));
        assertEquals(false,albumImpl.deleteAlbum("album100"));

    }
    @Test
    void givenAlbumNameToSearchAlbum() throws ParseException {
        assertEquals("al2314",albumImpl.searchByAlbumName("album1").getAlbumId());
    }

}
