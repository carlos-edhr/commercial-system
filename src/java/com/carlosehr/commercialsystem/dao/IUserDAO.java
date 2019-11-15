/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.exception.ErrorRegisterException;
import com.carlosehr.commercialsystem.models.User;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public interface IUserDAO {
    User createUser(User user) throws ErrorRegisterException;
    User validateUser(String user, String pass);
    
}
