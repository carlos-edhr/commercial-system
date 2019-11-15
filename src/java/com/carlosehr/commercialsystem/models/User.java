/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public class User {
    
      private long userId;
    private String user;
    private String password;
    
    public User(){}

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean validateEmail(){
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

 
        Matcher matcher = pattern.matcher(this.user);
        
        return matcher.find();
    }
    
}
