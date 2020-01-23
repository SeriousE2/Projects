/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.dao;

import com.mycompany.moviedatabase.dto.Movie;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class MovieDataBaseDaoFileImpl implements MovieDataBaseDao {

    private static final String FILE_PATH = "movies";
    private static final String DELIMITER = "::";

    @Override
    public Movie create(Movie movie) throws DataException {
        int movieId = 0;
        List<Movie> all = getAllMovies();
        for (Movie m : all) {
            movieId = Math.max(movieId, m.getMovieId());
        }
        movie.setMovieId(movieId + 1);
        all.add(movie);

        save(all);
        return movie;
    }

    //*************************************************************
    //*************************************************************
    @Override
    public boolean deleteMovie(int movieId) throws DataException {
        List<Movie> all = getAllMovies();
        for (Movie m : all) {
            if (m.getMovieId() == movieId) {
                all.remove(m);
                save(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateMovie(Movie movie) throws DataException {
        List<Movie> all = getAllMovies();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getMovieId() == movie.getMovieId()) {
                all.set(i, movie);
                save(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Movie> getAllMovies() throws DataException {
        ArrayList<Movie> all = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Movie movie = unmarshall(line);
                if (movie != null) {
                    all.add(movie);
                }
            }
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
        return all;
    }

    @Override
    public Movie getMoviebyId(int movieId) throws DataException {
        for (Movie m : getAllMovies()) {
            if (m.getMovieId() == movieId) {
                return m;
            }
        }
        return null;
    }

    private void save(List<Movie> movies) throws DataException {
        try ( PrintWriter movieWriter = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Movie m : movies) {
                movieWriter.println(marshall(m));
            }
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
    }

    private String marshall(Movie m) {
        return m.getMovieId() + DELIMITER
                + m.getTitle() + DELIMITER
                + m.getReleaseDate() + DELIMITER
                + m.getMpaaRating() + DELIMITER
                + m.getDirectorsName() + DELIMITER
                + m.getStudio() + DELIMITER
                + m.getUserRating() + DELIMITER;
    }

    private Movie unmarshall(String line) {

        String[] tokens = line.split(DELIMITER);
        if (tokens.length != 7) {
            return null;
        }

        Movie movie = new Movie();
        movie.setMovieId(Integer.parseInt(tokens[0]));
        movie.setTitle(tokens[1]);
        movie.setReleaseDate(tokens[2]);
        movie.setMpaaRating(tokens[3]);
        movie.setDirectorsName(tokens[4]);
        movie.setStudio(tokens[5]);
        movie.setUserRating(tokens[6]);
        return movie;

    }

    @Override
    public List<Movie> searchByTitle(String aTitle) throws DataException {

        aTitle = aTitle.toLowerCase();

        List<Movie> result = new ArrayList<>();

        for (Movie m : getAllMovies()) {

            if (m.getTitle().toLowerCase().contains(aTitle)) {
                result.add(m);
            }
        }
        return result;
    }
}
