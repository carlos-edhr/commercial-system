package com.carlosehr.commercialsystem.models;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.carlosehr.commercialsystem.models.Employee;

public class Order {
    

    private long orderId;
    private Employee employee;
    private Client client;
    private Date orderDate;
    private double discount;
    private double total;
    private String roundedTotal;
    private List<OrderDetail> details = new ArrayList<>();
      
          public Order() {
    }

    public Order(long orderId, Client client, Employee employee, Date orderDate, long discount, double total) {
        this.orderId = orderId;
        this.employee = employee;
        this.client = client;
        this.orderDate = orderDate;
        this.discount = discount;
        this.total = total;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    public void setRoundedTotal(String roundedTotal) {
        this.roundedTotal = roundedTotal;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public String getRoundedTotal() {
        return new DecimalFormat("#.##").format(total);
    }
    
    
    
}
