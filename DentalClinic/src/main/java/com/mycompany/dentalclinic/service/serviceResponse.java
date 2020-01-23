/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.service;

/**
 *
 * @author Eddy
 */
public class serviceResponse<T> extends Response{
    private T value;

    public serviceResponse(T value) {
        this.value = value;
    }

    public serviceResponse() {
    }

    public T getValue() {
        return value;
    }


    public void setValue(T value) {
        this.value = value;
    }

}
