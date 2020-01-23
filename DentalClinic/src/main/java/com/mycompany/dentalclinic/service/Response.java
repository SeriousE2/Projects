package com.mycompany.dentalclinic.service;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private final ArrayList<String> errorMessages = new ArrayList<>();

    public boolean hasError() {
        return errorMessages.size() > 0;
    }

    public void addError(String message) {
        errorMessages.add(message);
    }

    public List<String> getErrors() {
        return new ArrayList<>(errorMessages);
    }
}
