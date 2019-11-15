/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.models.Category;
import java.util.List;

/**
 *
 * @author Sylvia
 */
public interface ICategoryDAO {
    
     public List<Category> listAll();
    public String insert(Category cat);
    public String update(Category cat);
    public Category findById(long id);
    public String delete(Category cat);
    List<Category> searchByCriteria(String param);
    
}
