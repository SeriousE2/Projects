/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rockpaperscissors;

import java.util.Scanner;

/**
 *
 * @author Eddy
 */
public class HumanPlayer implements Player {

    Scanner input;

    public HumanPlayer(Scanner scanner) {
        input = scanner;
    }

    @Override
    public int getChoice() {
        int userInput;
        do {

            System.out.println("Player one please enter input: Rock = 1: Paper = 2: Scissors = 3");
            userInput = input.nextInt();
            if ((userInput < 1 || userInput > 3)) {
                System.out.println("Player one please enter a valid input Rock = 1: Paper = 2: Scissors = 3");
            }
        } while (userInput < 1 || userInput > 3);
        return userInput;
    }

}
