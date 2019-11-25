/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rockpaperscissors;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Eddy
 */
public class RockPaperScissors {
    public static void main(String[] args) {
        int rock = 1;
        int paper = 2;
        int scissors = 3;
        int gameRounds;
        
        
        
        int userInput;
        int cpuInput;

        Scanner input = new Scanner(System.in);
        Random number = new Random();
        
        
        // Loop asking the user if they want to play again.
        do {
            
            // The Game
        System.out.println("Rock, Paper, Scissors");
        System.out.println("Please enter amount of rounds. Max: 10  Minimal: 1");
        gameRounds = input.nextInt();
        
            
            if(gameRounds <= 10 && gameRounds >= 1)
            {
                int tied = 0;
                int playerWins = 0;
                int computerWins = 0;
                    
                    for(int i = 1; i <= gameRounds; i++){
                        
                        do{           
                        
                        System.out.println("Player one please enter input: Rock = 1: Paper = 2: Scissors = 3");
                        userInput = input.nextInt();
                            if ((userInput < 0 || userInput > 3)){
                                System.out.println("Player one please enter a valid input Rock = 1: Paper = 2: Scissors = 3");
                            }
                        }while(userInput < 1 || userInput > 3);
                    
                    
                            cpuInput = number.nextInt(3)+1;
                            System.out.println("Computer Player entered: " + cpuInput);

                            // Tied if statements
                            if (userInput == cpuInput){
                                tied++;
                                System.out.println("Draw please try again");
                            }
                            // Player one win if statements.
                            else if (userInput == rock && cpuInput == scissors || userInput == scissors && cpuInput == paper || userInput == paper && cpuInput == rock) {
                                playerWins++;
                                System.out.println("Player one wins!");
                            }  
                            // Computer Player win if statements.
                            else if (userInput == paper && cpuInput == rock || userInput == paper && cpuInput == scissors || userInput == rock && cpuInput == paper) {
                                computerWins++;
                                System.out.println("Computer Player wins!");
                            }              
                        }
                    
                        // Total Game Results
                        System.out.println("\nYour Rock, Paper, Scissors Total");
                        System.out.println("==================================");
                        System.out.println("Player One Score: " + playerWins);
                        System.out.println("Computer Player Score: " + computerWins);
                        System.out.println("Tied Games " + tied);
                        if(playerWins > computerWins){
                            System.out.println("The Winner Of This Set Goes To Player One!");
                        }
                        else if (playerWins == computerWins){
                            System.out.println("Draw!");
                        }
                        else{
                            System.out.println("The Winner Of This Set Goes To Computer Player!");
                        }
                

                        }
                       else
                        {
                            System.out.println("Invalid Entry: Game Over");
                        }
            
        } while(getAnswer("Do you want to play again? [y/n]"));
        System.out.println("Game Over!");
        System.out.println("Thanks for playing!");
 
    }
    
    public static boolean getAnswer(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextLine().equalsIgnoreCase("y");
    }
    
}