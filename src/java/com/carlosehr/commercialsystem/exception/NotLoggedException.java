/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.exception;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public class NotLoggedException extends UserException{
    public NotLoggedException(String message){
       super(message);
    }
}