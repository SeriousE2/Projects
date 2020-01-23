package com.mycompany.dentalclinic.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleIO {

    private static final String INVALID_NUMBER
            = "[INVALID] Enter a valid number.";
    private static final String NUMBER_OUT_OF_RANGE
            = "[INVALID] Enter a number between %s and %s.";
    private static final String REQUIRED
            = "[INVALID] Value is required.";

    private final Scanner scanner = new Scanner(System.in);

    public void print(String message) {
        System.out.println(message);
    }

    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }

    
    
    public String readRequiredString(String prompt) {
        while (true) {
            String result = readString(prompt);
            if (!result.isBlank()) {
                return result;
            }
            print(REQUIRED);
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readRequiredString(prompt));
            } catch (NumberFormatException ex) {
                print(INVALID_NUMBER);
            }
        }
    }

    public double readDouble(String prompt, double min, double max) {
        while (true) {
            double result = readDouble(prompt);
            if (result >= min && result <= max) {
                return result;
            }
            print(String.format(NUMBER_OUT_OF_RANGE, min, max));
        }
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readRequiredString(prompt));
            } catch (NumberFormatException ex) {
                print(INVALID_NUMBER);
            }
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            int result = readInt(prompt);
            if (result >= min && result <= max) {
                return result;
            }
            print(String.format(NUMBER_OUT_OF_RANGE, min, max));
        }
    }

    public boolean readBoolean(String prompt) {
        while (true) {
            String input = readRequiredString(prompt).toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            }
            print("[INVALID] Please enter 'y' or 'n'.");
        }
    }
    
    public BigDecimal readBigDecimal(String prompt) {
        String string;
        print(prompt);
        
        while(true) {
            string = scanner.nextLine();
            if(string.matches("\\d*\\.\\d{0,2}")) return new BigDecimal(string);
            print("Enter money example: 'dollars.cents'");
           
        }
    }
    
        public LocalTime readTime(String prompt) {
        boolean valid = false;
        LocalTime result = null;
        do {
            String value = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                value = readString(prompt);
                result = LocalTime.parse(value, formatter);
                valid = true;
            } catch (DateTimeParseException ex) {
                System.out.println("Sorry, the input is not in the valid time format. (HH:mm)\n");
            }
        } while (!valid);
        return result;
    }
    
    public LocalTime readLocalTime(String prompt) {
    while (true) {
            try {
                LocalTime.parse(readRequiredString(prompt));
            } catch (NumberFormatException ex){
                print("[INVALID TIME]");
            }
        }
    }
    
    
    public LocalDate readLocalDate(String prompt) {
        print(prompt);
        boolean success = false;
        while (!success) {
            String userInput = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate parsedDate = LocalDate.parse(userInput, formatter);
                success = true;
                return parsedDate;
            } catch (DateTimeParseException e) {
                System.out.println("[INVALID DATE FORMAT]" + prompt);
            }
        }
        return null;
    }

}
