/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.models.Order;
import com.carlosehr.commercialsystem.models.OrderDetail;
import java.util.List;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public interface IOrderDAO {
    
        
    List<Order> listAll();
    List<OrderDetail> details(Order order);
    Order findById(long idOrder);
    Order insert(Order order);
    Order delete(Order order);
    
}
