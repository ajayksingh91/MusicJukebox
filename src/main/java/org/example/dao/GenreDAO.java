package org.example.dao;

import org.example.model.Genre;

import java.util.List;

public interface GenreDAO {

    List<Genre> viewAllGenres();
    Genre searchByGenreId(String genreId);
    Genre searchByGenreName(String genreName);
    boolean insertGenre(Genre genre);
    void displayGenre(List<Genre> genres);
    boolean deleteGenre(String genreName);
    boolean updateGenre(Genre genre);
}
