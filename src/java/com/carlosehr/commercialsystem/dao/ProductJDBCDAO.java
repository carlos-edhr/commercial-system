
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.connections.DataBasePG;
import com.carlosehr.commercialsystem.models.Category;
import com.carlosehr.commercialsystem.models.Product;
import com.carlosehr.commercialsystem.models.Provider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public class ProductJDBCDAO implements IProductDAO {
    
    
     @Override
    public List<Product> listAll() {
        Product product;
        List<Product> listProducts = new ArrayList<>();
        DataBasePG dataBase = new DataBasePG();
        try {
            String sql = "select * from product";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id = rs.getLong("product_id");
                long idProv = rs.getLong("provider_id");
                long idCat = rs.getLong("category_id");
                String desc = rs.getString("description");
                double price = rs.getDouble("unit_price");
                int stock = rs.getInt("stock");
                
                Category cat = new CategoryJDBCDAO().findById(idCat);
                Provider prov = new ProviderJDBCDAO().findById(idProv);
                
                product = new Product(id, prov, cat, desc, price, stock);
                
                listProducts.add(product);
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            System.out.println("Error at listAll - Products: " + 
                    ex.getMessage());
            dataBase.disconnectDB();
        }
        
        return listProducts;
    }

    @Override
    public String insert(Product prod) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        try {
            
            String sql = "insert into product (product_id, provider_id, "
                    + "category_id, description, unit_price, stock) "
                    + "values(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, prod.getProductId());
            ps.setLong(2, prod.getProvider().getProvider_id());
            ps.setLong(3, prod.getCategory().getCategory_id());
            ps.setString(4, prod.getDescription());
            ps.setDouble(5, prod.getUnitPrice());
            ps.setInt(6, prod.getStock());
            
            ps.executeUpdate();
            message = "Product created.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to register product: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public String update(Product prod) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        try {
            
            String sql = "UPDATE product SET "
                    + "provider_id=?, category_id=?, description=?, "
                    + "unit_price=?, stock=? where product_id=?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, prod.getProvider().getProvider_id());
            ps.setLong(2, prod.getCategory().getCategory_id());
            ps.setString(3, prod.getDescription());
            ps.setDouble(4, prod.getUnitPrice());
            ps.setInt(5, prod.getStock());
            ps.setLong(6, prod.getProductId());
            
            ps.executeUpdate();
            message = "Product data updated.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to update product data: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public String delete(Product prod) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "delete from product where product_id=?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, prod.getProductId());
            ps.executeUpdate();
            message = "Product file deleted";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to delete this Product: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public Product findById(long idProd) {
        Product prod = null;
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "select * from product where product_id=? LIMIT 1";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, idProd);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                long id = rs.getLong("product_id");
                long idProv = rs.getLong("provider_id");
                long idCat = rs.getLong("category_id");
                String desc = rs.getString("description");
                double price = rs.getDouble("unit_price");
                int stock = rs.getInt("stock");
                
                Category cat = new CategoryJDBCDAO().findById(idCat);
                Provider prov = new ProviderJDBCDAO().findById(idProv);
                
                prod = new Product(id, prov, cat, desc, price, stock);
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            dataBase.disconnectDB();
        }
        
        return prod;
    }

    @Override
    public List<Product> getProductsByCategory(Category paramCat) {
        Product product;
        List<Product> listProducts = new ArrayList<>();
        DataBasePG dataBase = new DataBasePG();
        try {
            String sql = "select * from product where category_id = ?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, paramCat.getCategory_id());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                long id = rs.getLong("product_id");
                long idProv = rs.getLong("provider_id");
                long idCat = rs.getLong("category_id");
                String desc = rs.getString("description");
                double price = rs.getDouble("unit_price");
                int stock = rs.getInt("stock");
                
                Category cat = new CategoryJDBCDAO().findById(idCat);
                Provider prov = new ProviderJDBCDAO().findById(idProv);
                
                product = new Product(id, prov, cat, desc, price, stock);
                
                listProducts.add(product);
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            System.out.println("Error at listAll - Products: " + 
                    ex.getMessage());
            dataBase.disconnectDB();
        }
        
        return listProducts;
    }
    
    @Override
    public List<Product> searchByCriteria(String param) {
        Product product;
        List<Product> listProducts = new ArrayList<>();
        DataBasePG dataBase = new DataBasePG();
        try {
            String sql = "select * from product where descripcion ILIKE ?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setString(1, "%"+param+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id = rs.getLong("product_id");
                long idProv = rs.getLong("provider_id");
                long idCat = rs.getLong("category_id");
                String desc = rs.getString("description");
                double price = rs.getDouble("unit_price");
                int stock = rs.getInt("stock");
                
                Category cat = new CategoryJDBCDAO().findById(idCat);
                Provider prov = new ProviderJDBCDAO().findById(idProv);
                
                product = new Product(id, prov, cat, desc, price, stock);
                
                listProducts.add(product);
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            System.out.println("Error at searchByCriteria - Product: " + 
                    ex.getMessage());
            dataBase.disconnectDB();
        } finally {
            if (dataBase.getConnection()!=null) {
                dataBase.disconnectDB();
            }
        }
        
        return listProducts;
    }
    
}
