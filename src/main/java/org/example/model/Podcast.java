package org.example.model;

public class Podcast {

    private String podcastId;
    private String podcastName;
    private Double duration;
    private String dateOfPublish;
    private String artistId;
    private String location;

    public Podcast(String podcastId, String podcastName, Double duration, String dateOfPublish, String artistId, String location) {
        this.podcastId = podcastId;
        this.podcastName = podcastName;
        this.duration = duration;
        this.dateOfPublish = dateOfPublish;
        this.artistId = artistId;
        this.location = location;
    }

    public String getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(String podcastId) {
        this.podcastId = podcastId;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(String dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
