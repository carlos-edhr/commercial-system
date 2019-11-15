
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.connections.DataBasePG;
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
public class ProviderJDBCDAO implements IProviderDAO{
    
    @Override
    public List<Provider> listAll() {
        Provider prov;
        List<Provider> listProviders = new ArrayList<>();
        DataBasePG dataBase = new DataBasePG();
        try {
            String sql = "select * from provider";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id = rs.getInt("provider_id");
                String nameProvider = rs.getString("name");
                String contact = rs.getString("contact");
                String cellphone = rs.getString("cellphone");
                String phone = rs.getString("phone");
                prov = new Provider(id, nameProvider, contact, cellphone, phone);
                listProviders.add(prov);
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            System.out.println("Error at listAll - Provider: " + 
                    ex.getMessage());
            dataBase.disconnectDB();
        }
        
        return listProviders;
    }

    @Override
    public String insert(Provider prov) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        try {
            
            String sql = "insert into provider (provider_id, name, "
                    + "contact, cellphone, phone) "
                    + "values(?, ?, ?, ?, ?)";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, prov.getProvider_id());
            ps.setString(2, prov.getName());
            ps.setString(3, prov.getContact());
            ps.setString(4, prov.getCellphone());
            ps.setString(5, prov.getPhone());
            ps.executeUpdate();
            message = "Provider file created.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to register this provider: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public String update(Provider prov) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "UPDATE provider SET name = ?,"
                    + "contact=?, cellphone=?, phone=?  "
                    + "WHERE provider_id = ?";
            PreparedStatement ps = 
                    dataBase.getConnection().prepareStatement(sql);
            ps.setString(1, prov.getName());
            ps.setString(2, prov.getContact());
            ps.setString(3, prov.getCellphone());
            ps.setString(4, prov.getPhone());
            ps.setLong(5, prov.getProvider_id());
            ps.executeUpdate();
            
            message = "Provider data updated.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to update provider file: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public String delete(Provider prov) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "delete from provider where provider_id=?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, prov.getProvider_id());
            ps.executeUpdate();
            message = "Provider file deleted.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to delete provider file: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public Provider findById(long idProvider) {
        Provider prov = null;
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "select * from provider where provider_id=? LIMIT 1";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, idProvider);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                String cellphone = rs.getString("cellphone");
                String phone = rs.getString("phone");
                prov = new Provider(idProvider, name, contact, cellphone, phone);
                
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            dataBase.disconnectDB();
        }
        
        return prov;
    }
    
}
