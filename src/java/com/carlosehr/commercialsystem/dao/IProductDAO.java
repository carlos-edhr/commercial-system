/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.models.Category;
import com.carlosehr.commercialsystem.models.Product;
import java.util.List;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public interface IProductDAO {
    
     public List<Product> listAll();
    public String insert(Product prod);
    public String update(Product prod);
    public String delete(Product prod);
    public Product findById(long idProd);
    public List<Product> getProductsByCategory(Category paramCat);  
    List<Product> searchByCriteria(String param) ;
    
}
