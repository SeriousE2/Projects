/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;
import static java.math.RoundingMode.DOWN;

/**
 *
 * @author Eddy
 */
public class Change {

    private int quarters;  
    private int dimes;  
    private int nickels;  
    private int pennies;  
    
    public Change(BigDecimal change) {
        
        BigDecimal hundred =  new BigDecimal("100");
        BigDecimal moneyInPennies = change.multiply(hundred);
        BigDecimal quarter = new BigDecimal("25");
        BigDecimal dime = new BigDecimal("10");
        BigDecimal nickel = new BigDecimal("5");
        
        BigDecimal numberOfQuaters = moneyInPennies.divide(quarter, 0, DOWN);
        BigDecimal moneyToSubtract = numberOfQuaters.multiply(quarter);
        moneyInPennies = moneyInPennies.subtract(moneyToSubtract);
        
        BigDecimal numberOfDimes = moneyInPennies.divide(dime, 0, DOWN);
        moneyToSubtract = numberOfDimes.multiply(dime);
        moneyInPennies = moneyInPennies.subtract(moneyToSubtract);
        
        BigDecimal numberOfNickels = moneyInPennies.divide(nickel, 0, DOWN);
        moneyToSubtract = numberOfNickels.multiply(nickel);
        moneyInPennies = moneyInPennies.subtract(moneyToSubtract);
        
        BigDecimal numberOfPennies = moneyInPennies;
        
        quarters = numberOfQuaters.intValue();
        dimes = numberOfDimes.intValue();
        nickels = numberOfNickels.intValue();
        pennies = numberOfPennies.intValue();

//        this.quarters = numberOfQuaters.intValue();
//        this.dimes = numberOfDimes.intValue();
//        this.nickels = numberOfNickels.intValue();
//        this.pennies = numberOfPennies.intValue();
 
    }

    public Change(Change change) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }
}
