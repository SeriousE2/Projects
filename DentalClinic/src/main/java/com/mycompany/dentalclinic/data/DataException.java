package com.mycompany.dentalclinic.data;

public class DataException extends Exception {

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable innerEx) {
        super(message, innerEx);
    }
}
