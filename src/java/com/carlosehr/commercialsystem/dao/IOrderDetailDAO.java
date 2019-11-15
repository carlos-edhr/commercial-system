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
public interface IOrderDetailDAO {
      List<OrderDetail> getDetails(Order order);
    List<OrderDetail> getDetails(long idOrder);
    OrderDetail insert(OrderDetail detail);
    OrderDetail delete(OrderDetail detail);
}
