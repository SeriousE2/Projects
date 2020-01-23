/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.dao;

import com.mycompany.moviedatabase.dto.Movie;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface MovieDataBaseDao {

    Movie create(Movie movie) throws DataException;
    
    boolean deleteMovie(int movieId) throws DataException;
    
    boolean updateMovie(Movie movie) throws DataException;
    
    List<Movie> getAllMovies() throws DataException;
    
    Movie getMoviebyId(int movieId) throws DataException;

    List<Movie> searchByTitle(String aTitle) throws DataException;
    

}
