/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.controller;

import com.mycompany.moviedatabase.dao.DataException;
import com.mycompany.moviedatabase.dao.MovieDataBaseDao;
import com.mycompany.moviedatabase.dto.Movie;
import com.mycompany.moviedatabase.ui.MovieDataBaseView;
import com.mycompany.moviedatabase.ui.UserIO;
import com.mycompany.moviedatabase.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class MovieDataBaseController {

    private final MovieDataBaseView view;
    private final MovieDataBaseDao dao;

    public MovieDataBaseController(MovieDataBaseView view, MovieDataBaseDao dao) {
        this.view = view;
        this.dao = dao;
    }

    private final UserIO io = new UserIOConsoleImpl();

    public void run() throws DataException {
        int menuSelection = 0;
        do {
            menuSelection = view.printMenuAndGetSelection();
            switch (menuSelection) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    deleteMovie();
                    break;
                case 3:
                    editMovie();
                    break;
                case 4:
                    viewMovieById();
                    break;
                case 5:
                    displayAllMovies();
                    break;
                case 6:
                    movieSearchByTitle();
                    break;

            }
        } while (menuSelection > 0);

        view.sayGoodbye();
    }

    public void addMovie() {
        Movie newMovie = view.createMovie();

        try {
            dao.create(newMovie);
        } catch (DataException ex) {
            view.errorMessage(ex);
        }
        view.displaySuccess(newMovie.getTitle() + " was added!");
    }

    private void displayAllMovies() {
        try {
            List<Movie> all = dao.getAllMovies();
            view.displayMovies(all);
        } catch (DataException ex) {
            view.errorMessage(ex);
        }
    }

    private void editMovie() {
        int movieId = view.readMovieId("Edit Movie");

        try {
            Movie movie = dao.getMoviebyId(movieId);
            if (movie == null) {
                view.displayMessage("Movie Id " + movieId + " not found.");
                return;
            }
            movie = view.updateMovie(movie);
            if (dao.updateMovie(movie)) {
                view.displayMessage(movie.getTitle() + " Update Completed!");
            } else {
                view.displayMessage("Movie Id " + movieId + " not found.");
            }
        } catch (DataException ex) {
            view.errorMessage(ex);
        }
    }

    private void movieSearchByTitle() {

        try {
            view.searchByTitleBanner();
            String aTitle = view.getMovieSearched();
            List<Movie> results = dao.searchByTitle(aTitle);
            view.displayMovies(results);
        } catch (DataException ex) {
            view.errorMessage(ex);
        }

    }

    private void deleteMovie() {
        try {
            int movieId = view.readMovieId("Delete Movie!");
            if (dao.deleteMovie(movieId)) {
                view.displayMessage("Movie Id " + movieId + " deleted.");
            } else {
                view.displayMessage("Movie Id " + movieId + " not found.");
            }
        } catch (DataException ex) {
            view.errorMessage(ex);
        }
    }

    private void viewMovieById() throws DataException {

        int movieId = view.readMovieId("Movie Id Search");
        Movie movie = dao.getMoviebyId(movieId);
        view.displayMovie(movie);
    }
}
