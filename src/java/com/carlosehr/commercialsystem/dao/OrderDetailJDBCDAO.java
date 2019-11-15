
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.connections.DataBasePG;
import com.carlosehr.commercialsystem.models.Order;
import com.carlosehr.commercialsystem.models.OrderDetail;
import com.carlosehr.commercialsystem.models.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailJDBCDAO implements IOrderDetailDAO{
    
     @Override
    public List<OrderDetail> getDetails(Order order) {
        OrderDetail detail;
        List<OrderDetail> details = new ArrayList<>();
        
        ProductJDBCDAO daoProduct = new ProductJDBCDAO();
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "select * from order_detail where order_id = ?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, order.getOrderId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                 long detailId = rs.getLong("detail_id");
                long productId = rs.getLong("product_id");
                double amount = rs.getDouble("amount");
                double total = rs.getDouble("total");
                
                Product prod = daoProduct.findById(productId);
                
                
                detail = new OrderDetail(detailId, order, prod, amount, total);
                details.add(detail);
            }
            
            dataBase.disconnectDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if (dataBase.getConnection() != null) {
                dataBase.disconnectDB();
            }
        }
        return details;
    }

    @Override
    public List<OrderDetail> getDetails(long idOrder) {
        return null;
    }

    @Override
    public OrderDetail insert(OrderDetail detail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderDetail delete(OrderDetail detail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

