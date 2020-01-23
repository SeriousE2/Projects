/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase;

import com.mycompany.moviedatabase.controller.MovieDataBaseController;
import com.mycompany.moviedatabase.dao.DataException;
import com.mycompany.moviedatabase.dao.MovieDataBaseDao;
import com.mycompany.moviedatabase.dao.MovieDataBaseDaoFileImpl;
import com.mycompany.moviedatabase.ui.MovieDataBaseView;
import com.mycompany.moviedatabase.ui.UserIOConsoleImpl;

/**
 *
 * @author Eddy
 */
public class App {

    public static void main(String[] args) throws DataException {

        UserIOConsoleImpl io = new UserIOConsoleImpl();
        MovieDataBaseView view = new MovieDataBaseView(io);
        MovieDataBaseDao dao = new MovieDataBaseDaoFileImpl();
        MovieDataBaseController controller = new MovieDataBaseController(view, dao);
        controller.run();
    }
}
