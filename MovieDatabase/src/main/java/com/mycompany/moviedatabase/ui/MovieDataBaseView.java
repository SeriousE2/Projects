/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.ui;

import com.mycompany.moviedatabase.dao.DataException;
import com.mycompany.moviedatabase.dto.Movie;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class MovieDataBaseView {

    private final UserIOConsoleImpl io;

    public MovieDataBaseView(UserIOConsoleImpl io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("==============");
        io.print("1. Add New Movie!");
        io.print("2. Remove a Movie.");
        io.print("3. Edit the Information for an Existing Movie.");
        io.print("4. Display information for a single Movie by movie ID.");
        io.print("5. List all Movies in the database.");
        io.print("6. Search Movies in the database by Title.");
        io.print("0. Exit");

        return io.readInt("Please select from the above choices.", 0, 6);
    }

    public void sayGoodbye() {
        io.print("\nGoodbye");
    }
    
    
    // *************************************************************************
    public void searchByTitleBanner(){
        io.print("\nSearch Movie By Title");
        io.print("========================");
    }
    
    public String getMovieSearched(){
       String search = io.readString("Please enter movie title to begin search.");
       return search;
    } 
    // *************************************************************************
    
    
    public Movie createMovie() {
        io.print("\nAdd new Movie");
        io.print("===============");

        Movie newMovie = new Movie();
        io.print("Please add new movie title.");
        newMovie.setTitle(io.readString(""));
        newMovie.setReleaseDate(io.readString("Please enter movie release date."));
        newMovie.setMpaaRating(io.readString("Please enter movie MPAA Rating."));
        newMovie.setDirectorsName(io.readString("Please enter directors Name."));
        newMovie.setStudio(io.readString("Please enter movie studio's name."));
        newMovie.setUserRating(io.readString("Please enter User Ratings"));
        newMovie.setMovieId(io.readInt("Please enter movie ID"));

        return newMovie;
    }

    public int readMovieId(String header) {
        io.print("");
        io.print(header);
        io.print("================");
        return io.readInt("Please enter Movie Id:");
    }

    public void displayMovies(List<Movie> all) {
        io.print("");
        io.print("\nAll Movies");
        io.print("=================");
        if (all.isEmpty()) {
            io.print("No movies found");
            return;
        }

        for (Movie m : all){
            io.print(m.getMovieId() + ": " + m.getTitle() + ": " + m.getReleaseDate()
            + ": " + m.getMpaaRating() + ": " + m.getDirectorsName() + ": " + m.getStudio()
            + ": " + m.getUserRating());
        }
    }
    public void displayMovie(Movie movie){
        if (movie != null){
            io.print(movie.getTitle());
            io.print(movie.getReleaseDate());
            io.print(movie.getMpaaRating());
            io.print(movie.getDirectorsName());
            io.print(movie.getStudio());
            io.print(movie.getUserRating());
        } else {
            io.print("No movie found.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void errorMessage(DataException ex) {
        io.readString(ex.getMessage() + "\nPress enter to continue");
    }

    public void displaySuccess(String prompt) {
        io.readString(prompt + "\nPress enter to continue");
    }
    
    public void displayMessage(String message) {
        io.print(message);
    }
    

    public Movie updateMovie(Movie movie) {
        
        String title = io.readString(
                String.format("Please enter title (%s):", movie.getTitle()));
        if (!title.isEmpty()){
            movie.setTitle(title);
        }
        
        String releaseDate = io.readString(
                String.format("Please enter release date (%s):", movie.getReleaseDate()));
        if (!releaseDate.isEmpty()){
            movie.setReleaseDate(releaseDate);
        }
        
        String mpaaRating = io.readString(
                String.format("Please enter MPAA Rating (%s):", movie.getMpaaRating()));
        if (!mpaaRating.isEmpty()){
            movie.setMpaaRating(mpaaRating);
        }
        
        String directorsName = io.readString(
                String.format("Please enter Directors Name (%s):", movie.getDirectorsName()));
        if (!directorsName.isEmpty()){
            movie.setDirectorsName(directorsName);
        }
        
        String studio = io.readString(
                String.format("Please enter studio (%s):", movie.getStudio()));
        if (!studio.isEmpty()){
            movie.setStudio(studio);
        }
        
        String userRating = io.readString(
                String.format("Please enter User Rating (%s):", movie.getUserRating()));
        if(!userRating.isEmpty()){
            movie.setUserRating(userRating);
        }
        return movie;
    }
    
}
