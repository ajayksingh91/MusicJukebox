package org.example.driver;

import org.example.dao.*;
import org.example.impl.*;
import org.example.model.*;
import org.example.userdefinedexception.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class JukeBoxDriver {
    public static void main(String[] args) throws ParseException, UnsupportedAudioFileException, LineUnavailableException,
            IOException, NoSuchAlbumAvaiableException, EmptySongsListException, ArtistNotFoundException,
            EmptyPodcastListException, NoSuchSongAvailableException, NoPlaylistAvailableException,
            NoPodcastAvailableException {
        Scanner scanner = new Scanner(System.in);

        SongDAO songs = new SongsImpl();
        ArtistDAO artistDAO = new ArtistImpl();
        GenreDAO genreDAO = new GenreImpl();
        PodcastDAO podcastDAO = new PodcastImpl();
        PlaylistDAO playlistDAO = new PlaylistImpl();
        PlaylistCatalogDAO playlistCatalogDAO = new PlaylistCatalogImpl();
        AlbumDAO albumDAO = new AlbumImpl();
        PlayMusicImpl playMusic;
        System.out.println("\n******************************** !!! WELCOME TO JUKEBOX !!! ********************************");
        System.out.println("\n******************************** All Songs ********************************");

        List<Songs> songsList = songs.viewAllSongs();
        songs.displaySongs(songsList);


        int menuChoice;
        System.out.println("******** Choose From The Menu *********");
        do {
            System.out.println("1. Songs Details \t2. Podcast Details \t3. PlayList Details \t4. Exit");
            System.out.println("Enter your Choice : ");
            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                //--------------------------------------------------------------------------------------------------
                //choose the option from the below details
                case 1:
                    int songOption;
                    System.out.println("***** Songs Details *****");
                    do {
                        System.out.println("\n1. View All Songs \t2. Insert New Song \t3. Search for a Songs \t4. Exit");
                        System.out.println("Enter you choice : ");
                        songOption = scanner.nextInt();
                        switch (songOption) {
                            // showing all the songs present in the data base
                            case 1:
                                songsList = songs.viewAllSongs();
                                if (songsList.size() == 0) {
                                    throw new EmptySongsListException("No Songs present in the Songs List");
                                } else {
                                    songs.displaySongs(songsList);
                                }
                                break;
                            //inserting new song with user entered data into data base
                            case 2:
                                System.out.println("Enter Song ID : ");
                                String songId = scanner.next();
                                System.out.println("Enter song Name : ");
                                String sName = scanner.next();
                                System.out.println("Enter Duration : ");
                                double duration = scanner.nextDouble();
                                System.out.println("Enter Artist Name : ");
                                String aName = scanner.next();
                                Artist artistName = artistDAO.searchByArtistName(aName);
                                System.out.println("Enter Album Name : ");
                                String alName = scanner.next();
                                Album albumName = albumDAO.searchByAlbumName(alName);
                                System.out.println("Enter location : ");
                                String location = scanner.next();

                                Songs song = new Songs(songId, sName, duration, artistName.getArtistId(), location, albumName.getAlbumId());
                                boolean insertSong = songs.insertSong(song);
                                if (insertSong) {
                                    System.out.println("Song " + song.getSongName() + " is inserted successfully");
                                } else {
                                    System.out.println("Inserting of song is Failed ");
                                }
                                break;

                                //search by your choice as u want
                            case 3:
                                int search;
                                do {
                                    System.out.println("\n***** Choose your Choice *****");
                                    System.out.println("1. By Song name \t2. By Artist Name \t3. By Album Name \t4. Exit");
                                    search = scanner.nextInt();
                                    switch (search) {

                                        // search by the song name from the data base

                                        case 1:
                                            System.out.println("Enter Song name to search : ");
                                            String songName = scanner.next();
                                            List<Songs> songsBySongName = Collections.singletonList(songs.searchSongBySongName(songName));
                                            songs.displaySongs(songsBySongName);
                                            break;

                                            // search by the artist name

                                        case 2:
                                            System.out.println("Enter Artist name to search : ");
                                            String nameOfArtist = scanner.next();
                                            Artist artist = artistDAO.searchByArtistName(nameOfArtist);
                                            if (artist == null) {
                                                throw new ArtistNotFoundException(nameOfArtist + " is present in the Artist List");
                                            }
                                            List<Songs> songsByArtist = songs.searchSongByArtist(artist.getArtistId());

                                            if (songsByArtist.size() == 0) {
                                                throw new EmptySongsListException("No Songs present in the Songs List");
                                            } else {
                                                songs.displaySongs(songsByArtist);
                                            }

                                            break;

                                        // search song with the help of album name from data base

                                        case 3:
                                            System.out.println("Enter Album Name that you want to search for Songs : ");
                                            String nameOfAlbum = scanner.next();
                                            Album album = albumDAO.searchByAlbumName(nameOfAlbum);
                                            if (album == null) {
                                                throw new NoSuchAlbumAvaiableException(nameOfAlbum + " is not present for for any of the songs");
                                            }
                                            List<Songs> songsByAlbum = songs.searchSongByAlbum(album.getAlbumId());

                                            if (songsByAlbum.size() == 0) {
                                                throw new EmptySongsListException("No Songs present in the Songs List");
                                            } else {
                                                songs.displaySongs(songsByAlbum);
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                } while (search <= 4);
                                break;

                            default:
                                break;
                        }
                    } while (songOption <= 3);

                    break;

                //---------------------------------------------------------------------------------------------------
                case 2:

                    // user choice of podcast details from data base

                    int podcastSelect;
                    do {
                        System.out.println("1. View All Podcast \t2. Insert Podcast \t3. Search Podcast \t4. Exit");
                        System.out.println("Enter your choice : ");
                        podcastSelect = scanner.nextInt();

                        switch (podcastSelect) {
                            // show all the podcast present in the data base

                            case 1:
                                List<Podcast> podcastList = podcastDAO.viewAllPodcast();
                                if (podcastList.size() == 0) {
                                    throw new EmptyPodcastListException("No podcast available");
                                }
                                podcastDAO.displayPodcast(podcastList);

                                break;
                                // insert new podcast into the data base by user entering data

                            case 2:
                                System.out.println("Enter Podcast ID : ");
                                String podId = scanner.next();
                                System.out.println("Enter Podcast Name : ");
                                String pName = scanner.next();
                                System.out.println("Enter Duration : ");
                                double podDuration = scanner.nextDouble();
                                System.out.println("Enter Date of publish : ");
                                String dateConverted = scanner.next();
                                System.out.println("Artist Name : ");
                                String artistName = scanner.next();
                                Artist findArtist = artistDAO.searchByArtistName(artistName);
                                System.out.println("Enter Location : ");
                                String location = scanner.next();

                                Podcast podcast = new Podcast(podId, pName, podDuration, dateConverted, findArtist.getArtistId(), location);
                                boolean insertPodcast = podcastDAO.insertPodcast(podcast);
                                if (insertPodcast) {
                                    System.out.println("Song " + podcast.getPodcastName() + " is inserted successfully");
                                } else {
                                    System.out.println("Sorry Unable to insert Song");
                                }
                                break;
                                // choose how you want to search

                            case 3:
                                int podcastSearch;
                                do {
                                    System.out.println("\n***** Choose your choice *****");
                                    System.out.println("1. By Podcast Published Date \t2. By Artist \t3. Exit");
                                    System.out.println("Enter your choice : ");
                                    podcastSearch = scanner.nextInt();
                                    switch (podcastSearch) {

                                        //search podcast by date of publish

                                        case 1:
                                            System.out.println("Enter Date of publish to search :  ");
                                            String dateOfPublish = scanner.next();
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            Date publishedDate = dateFormat.parse(dateOfPublish);
                                            List<Podcast> podcasts = podcastDAO.searchByDateOfPublish(publishedDate);
                                            if (podcasts.size() == 0) {
                                                throw new EmptyPodcastListException("No podcast available for this Date");
                                            } else {
                                                podcastDAO.displayPodcast(podcasts);
                                            }

                                            break;
                                            // search podcast by artist name

                                        case 2:
                                            System.out.println("Enter Artist name to search : ");
                                            artistName = scanner.next();
                                            Artist artist = artistDAO.searchByArtistName(artistName);
                                            if (artist == null) {
                                                throw new ArtistNotFoundException(artistName + " is present in the Artist List");
                                            }
                                            List<Podcast> podcastListByArtist = podcastDAO.searchByArtist(artist.getArtistId());
                                            if (podcastListByArtist.size() == 0) {
                                                throw new EmptyPodcastListException("No podcast available for this Artist");
                                            } else {
                                                podcastDAO.displayPodcast(podcastListByArtist);
                                            }


                                            break;
                                        default:
                                            break;
                                    }
                                } while (podcastSearch <= 2);
                                break;
                            default:
                                break;
                        }
                    } while (podcastSelect <= 3);
                    break;

                //----------------------------------------------------------------------------------------------------
                case 3:
                    int playListChoice;
                    int start = 0;
                    int end = 9000;
                    String catId = "c" + (int) Math.floor(Math.random() * (end - start + 1) + start);

                    System.out.println("***** Playlist Details *****");
                    do {
                        System.out.println("\n1. View all item \t2. Insert Song to Playlist \t3. insert Podcast to playlist \t4. Music Play \t5. Exit");
                        System.out.println("Enter your Choice");
                        playListChoice = scanner.nextInt();

                        switch (playListChoice) {
                            // show all the catalog present in the data base

                            case 1:
                                List<Playlist> playlistsList = playlistDAO.getAllPlaylist();
                                playlistDAO.displayPlayList(playlistsList);
                                System.out.println("Enter your playlist name to view all songs of that playlist : ");
                                String playlistName = scanner.next();
                                Playlist playlist = playlistDAO.getPlayList(playlistName);
                                List<PlaylistCatalog> playlistCatalogList = playlistCatalogDAO.getAllPlayListCatalog(playlist.getPlaylistId());
                                songsList = songs.viewAllSongs();
                                if (songsList.size() == 0) {
                                    throw new EmptySongsListException("No Songs present in the Songs List");
                                }
                                List<Podcast> podcastList = podcastDAO.viewAllPodcast();
                                if (podcastList.size() == 0) {
                                    throw new EmptyPodcastListException("No podcast present in the podcast List");
                                }
                                int count = 1;


                                System.out.format("\n%-10s%-25s%-35s%-20s", "Sl.No", "Playlist Name", "Item Name", "Item Type");
                                System.out.println("\n*************************************************************************************************************");
                                for (PlaylistCatalog list : playlistCatalogList) {

                                    for (Songs songs1 : songsList) {
                                        if (list.getItemId().equalsIgnoreCase(songs1.getSongId())) {
                                            System.out.format("\n%-10s%-25s%-40s%-20s", count, playlist.getPlaylistName(), songs1.getSongName(), "Song");
                                            count++;
                                        }
                                    }

                                    for (Podcast podcast : podcastList) {
                                        if (list.getItemId().equalsIgnoreCase(podcast.getPodcastId())) {
                                            System.out.format("\n%-10s%-25s%-40s%-20s", count, playlist.getPlaylistName(), podcast.getPodcastName(), "Podcast");
                                            count++;
                                        }
                                    }
                                    //count++;
                                }
                                System.out.println("\n*************************************************************************************************************");
                                break;

                                //insert song into the playlist

                            case 2:
                                System.out.println("Enter The Playlist name : ");
                                playlistName = scanner.next();
                                System.out.println("Enter the Song Id : ");
                                String songId = scanner.next();
                                Songs songs2 = songs.getSongByID(songId);
                                if (songs == null) {
                                    throw new NoSuchSongAvailableException(songId + " is not present in the songs list");
                                }
                                Playlist playlistItem = playlistDAO.getPlayList(playlistName);
                                if (playlistItem == null) {
                                    throw new NoPlaylistAvailableException(playlistName + " is not present in the Playlist");
                                }
                                PlaylistCatalog playlistCatalog = new PlaylistCatalog(catId, playlistItem.getPlaylistId(), "Song", songs2.
                                        getSongId());
                                boolean insertItem = playlistCatalogDAO.insertItemToPlayListCatalog(playlistCatalog);
                                if (insertItem) {
                                    System.out.println("Song " + songs2.getSongName() + "is inserted successfully");
                                } else {
                                    System.out.println("Unable to insert song in the playlist");
                                }

                                break;

                                // insert podcast into the catalog

                            case 3:
                                System.out.println("Enter the Playlist Name : ");
                                String namePlaylist = scanner.next();
                                System.out.println("Enter the Podcast Name : ");
                                String podcastName = scanner.next();
                                Podcast podcast = podcastDAO.getPodcastByName(podcastName);
                                if (podcast == null) {
                                    throw new NoPodcastAvailableException(podcastName + " is not available in podcast in list");
                                }

                                Playlist pl = playlistDAO.getPlayList(namePlaylist);
                                if (pl == null) {
                                    throw new NoPlaylistAvailableException(namePlaylist + " is not present in the Playlist");
                                }

                                PlaylistCatalog insertPlaylistCatalog = new PlaylistCatalog(catId, pl.getPlaylistId(), "Podcast",
                                        podcast.getPodcastId());
                                boolean insertPodcast = playlistCatalogDAO.insertItemToPlayListCatalog(insertPlaylistCatalog);
                                if (insertPodcast) {
                                    System.out.println("Podcast " + podcast.getPodcastName() + "is inserted successfully");
                                } else {
                                    System.out.println("Unable to insert Podcast in the playlist");
                                }
                                break;

                                //playing the music

                            case 4:
                                System.out.println("********** Music ***********");
                                System.out.println("Enter your playlist name to play songs from that playlist : ");
                                String name = scanner.next();
                                Playlist nameOfPlaylist = playlistDAO.getPlayList(name);
                                if (nameOfPlaylist == null) {
                                    throw new NoPlaylistAvailableException(name + " is not present in the Playlist");
                                }
                                List<PlaylistCatalog> playlistCat = playlistCatalogDAO.getAllPlayListCatalog(nameOfPlaylist.getPlaylistId());

                                List<Songs> allSongsList = songs.viewAllSongs();
                                List<Podcast> allPodcastList = podcastDAO.viewAllPodcast();

                                System.out.println("Enter Sl.No of Song to play song : ");
                                int choice = scanner.nextInt();
                                int i = 1;
                                for (PlaylistCatalog allPlaylistCat : playlistCat) {
                                    if (choice == i && allPlaylistCat.getType().equalsIgnoreCase("song")) {
                                        for (Songs song : allSongsList) {
                                            if (allPlaylistCat.getItemId().equalsIgnoreCase(song.getSongId())) {
                                                String fileName = song.getLocation();
                                                playMusic = new PlayMusicImpl(fileName);
                                                playMusic.MusicPlay();
                                                break;
                                            }
                                        }
                                    } else if (choice == i && allPlaylistCat.getType().equalsIgnoreCase("podcast")) {
                                        for (Podcast podcasts : allPodcastList) {
                                            if (allPlaylistCat.getItemId().equalsIgnoreCase(podcasts.getPodcastId())) {
                                                playMusic = new PlayMusicImpl(podcasts.getLocation());
                                                playMusic.MusicPlay();
                                                break;
                                            }
                                        }
                                    }
                                    i++;
                                }

                                break;
                        }
                    } while (playListChoice <= 4);
                    break;

                default:
                    break;
            }
        } while (menuChoice <= 3);
    }
}

