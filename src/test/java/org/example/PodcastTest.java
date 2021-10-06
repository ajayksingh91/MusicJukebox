package org.example;

import org.example.impl.PodcastImpl;
import org.example.model.Podcast;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PodcastTest
{
    PodcastImpl podcastImpl;

    @BeforeEach
    void setUp()
    {
        podcastImpl=new PodcastImpl();
    }

    @AfterEach
    void tearDown()
    {
        podcastImpl=null;
    }

    @Test
    void givenDataToInsertPodcastIntoTable()  {

        assertEquals(true,podcastImpl.insertPodcast(new Podcast("p567","podcat2",
                12.12,"2021-02-12","a222","F:\\Music\\Groove-machine-electronic-synth-loop.wav")));
    }
    @Test
    void GivenPodcastIdToDeletePodcastFromtable()
    {
        assertEquals(false,podcastImpl.deletePodcast("p333"));
        assertEquals(false,podcastImpl.deletePodcast("p8761"));
    }


}
