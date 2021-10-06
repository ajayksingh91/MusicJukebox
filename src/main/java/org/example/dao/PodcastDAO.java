package org.example.dao;

import org.example.model.Podcast;

import java.util.Date;
import java.util.List;

public interface PodcastDAO {

    List<Podcast> viewAllPodcast();

    Podcast getPodcastByName(String celebrityName);

    Podcast getPodcastByDate(String date);

    boolean insertPodcast(Podcast podcast);

    boolean deletePodcast(String name);

    void displayPodcast(List<Podcast> podcasts);

    List<Podcast> searchByPodcastName(String podcastName);
    List<Podcast> searchByDateOfPublish(Date date);
    List<Podcast> searchByArtist(String artistName);
}
