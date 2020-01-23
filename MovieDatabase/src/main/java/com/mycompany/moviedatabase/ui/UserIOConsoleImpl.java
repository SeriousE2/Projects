/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase.ui;

import java.util.Scanner;

/**
 *
 * @author Eddy
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double out;
        do {
            out = Double.parseDouble(sc.nextLine());
            if (out < min || out > max) {
                System.out.println("That is not a valid double for the given range (" + min + "," + max + ")");
            }
        } while (out < min || out > max);
        return out;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return Float.parseFloat(sc.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        float out;
        do {
            out = Float.parseFloat(sc.nextLine());
            if (out < min || out > max) {
                System.out.println("That is not a valid float for the given range (" + min + "," + max + ")");
            }
        } while (out < min || out > max);
        return out;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int out;
        do {
            out = Integer.parseInt(sc.nextLine());
            if (out < min || out > max) {
                System.out.println("That is not a valid integer for the given range (" + min + "," + max + ")");
            }
        } while (out < min || out > max);
        return out;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return Long.parseLong(sc.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        long out;
        do {
            out = Long.parseLong(sc.nextLine());
            if (out < min || out > max) {
                System.out.println("That is not a valid long for the given range (" + min + "," + max + ")");
            }
        } while (out < min || out > max);
        return out;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return (sc.nextLine());
    }

}
