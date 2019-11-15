
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.connections.DataBasePG;
import com.carlosehr.commercialsystem.models.Client;
import com.carlosehr.commercialsystem.models.Employee;
import com.carlosehr.commercialsystem.models.Order;
import com.carlosehr.commercialsystem.models.OrderDetail;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public class OrderJDBCDAO implements IOrderDAO{

    @Override
    public List<Order> listAll() {
        Order order = null;
        Employee employee = null;
        Client client = null;
        List<Order> orders = new ArrayList<>();
        
        DataBasePG dataBase = new DataBasePG();
        
        EmployeeJDBCDAO daoEmp = new EmployeeJDBCDAO();
        ClientJDBCDAO daoClient = new ClientJDBCDAO();
        
        try {
            String sql = "select * from order";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id = rs.getLong("order_id");
                long idClient = rs.getLong("client_id");
                long idEmployee  = rs.getLong("employee_id");
                java.sql.Date date = rs.getDate("order_date");
                int discount = rs.getInt("discount");
                BigDecimal total = rs.getBigDecimal("total");
                
                employee = daoEmp.findById(idEmployee);
                client = daoClient.findById(idClient);
                
                if (employee != null || client != null) {
                    order = new Order(id, client,  employee, date, discount, total.doubleValue());
                    orders.add(order);
                }
            }
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            if (dataBase.getConnection()!=null) {
                dataBase.disconnectDB();
            }
        }
        
        return orders;
    }

    @Override
    public List<OrderDetail> details(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findById(long idOrden) {
        Order orden = null;
        Employee employee = null;
        Client client = null;
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "select * from order where order_id=? LIMIT 1";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, idOrden);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long idEmployee = rs.getLong("employee_id");
                long idClient = rs.getLong("client_id");
                java.sql.Date date = rs.getDate("order_date");
                long discount = rs.getInt("discount");
                BigDecimal total = rs.getBigDecimal("total");
                
                IEmployeeDAO daoEmp = new EmployeeJDBCDAO();
                IClientDAO daoClie = new ClientJDBCDAO();
                employee = daoEmp.findById(idEmployee);
                client = daoClie.findById(idClient);
                orden = new Order(idOrden, client, employee, date, discount, total.longValue());
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if (dataBase.getConnection()!=null) {
                dataBase.disconnectDB();
            }
        }
        
        return orden;
    }

    @Override
    public Order insert(Order order) {
        String message="";
        DataBasePG dataBase = new DataBasePG();
        try {
            int idGenerated = 0;
            dataBase.getConnection().setAutoCommit(false);
            String sql = "insert into order(client_id, employee_id, order_date, discount, total)"
                    + "values(?,?,?,?,?)";
            
            PreparedStatement ps = dataBase.getConnection().
                    prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, (int)order.getClient().getClientId());
            ps.setInt(2, (int)order.getEmployee().getEmployee_id());
            ps.setDate(3, order.getOrderDate());
            ps.setDouble(4, order.getDiscount());
            ps.setBigDecimal(5, new BigDecimal(order.getTotal()));
            
            ps.executeUpdate();
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerated = generatedKeys.getInt(1);
            }
            
            PreparedStatement ps2;
            
            for (OrderDetail detail : order.getDetails()) {
                String sqlDetails = "insert into order_detail "
                        + "(order_id, product_id, amount, total) "
                        + "values(?,?,?,?)";
                
                ps2 = dataBase.getConnection().prepareStatement(sqlDetails);
                ps2.setInt(1, idGenerated);
                ps2.setInt(2, (int)detail.getProduct().getProductId());
                ps2.setDouble(3, detail.getAmount());
                ps2.setBigDecimal(4, new BigDecimal(detail.getTotal()));
                
                ps2.executeUpdate();
                
            }
            
            message = "Order created.";
            dataBase.getConnection().commit();
            
            order.setOrderId(idGenerated);
            
        } catch (SQLException ex) {
            if (dataBase.getConnection() != null) {
                try {
                    System.err.print("Cannot process transaction.");
                    dataBase.getConnection().rollback();
                } catch(SQLException excep) {
                    System.err.println("Transaction rollback failled.");
                }
                
            }
            ex.printStackTrace();
            message = "Cannot register product: " + ex.getMessage();
            dataBase.disconnectDB();
        } finally {
        
            if (dataBase.getConnection()!=null) {
                dataBase.disconnectDB();
            }
        }
        
        return order;
    }

    @Override
    public Order delete(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
