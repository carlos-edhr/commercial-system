
package com.carlosehr.commercialsystem.models;

import java.text.DecimalFormat;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public class OrderDetail {
    private long detailId;
    private Order order;
    private Product product;
    private double amount;
    private double total;
    String roundedTotal;

    public OrderDetail() {
    }

   public OrderDetail(long detailId, Order order, Product product, double amount, double total) {
        this.detailId = detailId;
        this.order = order;
        this.product = product;
        this.amount = amount;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }
    
        public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRoundedTotal() {
        return new DecimalFormat("#.##").format(total);
    }

    public void setRoundedTotal(String tot) {
        this.roundedTotal = tot;
    }
    
    
}
