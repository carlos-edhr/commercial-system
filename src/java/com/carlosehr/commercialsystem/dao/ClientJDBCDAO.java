
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.connections.DataBasePG;
import com.carlosehr.commercialsystem.models.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Carlos Hoyos Rojas
 */
public class ClientJDBCDAO implements IClientDAO{
     @Override
    public List<Client> listAll() {
        Client client;
        List<Client> listClients = new ArrayList<>();
        DataBasePG dataBase = new DataBasePG();
        try {
            String sql = "select * from client";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id = rs.getLong("client_id");
                String idCard = rs.getString("id_card");
                String nameCompany = rs.getString("company_name");
                String nameContact = rs.getString("contact_name");
                String address = rs.getString("address");
                String fax = rs.getString("fax");
                String email = rs.getString("email");
                String cellphone = rs.getString("cellphone");
                String phone = rs.getString("phone");
                
                client = new Client(id, idCard, nameCompany, nameContact, address, fax, email, cellphone, phone);
                
                listClients.add(client);
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            System.out.println("Error en listAll clients: " + 
                    ex.getMessage());
            dataBase.disconnectDB();
        }
        
        return listClients;
    }

    @Override
    public String insert(Client cli) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        try {
            
            String sql = "insert into client (client_id, id_card, company_name, "
                    + "contact_name, address, fax, email, cellphone, phone) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, cli.getClientId());
            ps.setString(2, cli.getIdCard());
            ps.setString(3, cli.getCompanyName());
            ps.setString(4, cli.getContactName());
            ps.setString(5, cli.getAddress());
            ps.setString(6, cli.getFax());
            ps.setString(7, cli.getEmail());
            ps.setString(8, cli.getCellphone());
            ps.setString(9, cli.getPhone());
            
            ps.executeUpdate();
            message = "Client file created.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Cannot create Client file: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public String update(Client cli) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        try {
            
            String sql = "UPDATE client SET id_card=?, "
                    + "company_name=?, contact_name=?, address=?, "
                    + "fax=?, email=?, cellphone=?, phone=? where client_id=?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setString(1, cli.getIdCard());
            ps.setString(2, cli.getCompanyName());
            ps.setString(3, cli.getContactName());
            ps.setString(4, cli.getAddress());
            ps.setString(5, cli.getFax());
            ps.setString(6, cli.getEmail());
            ps.setString(7, cli.getCellphone());
            ps.setString(8, cli.getPhone());
             ps.setLong(9, cli.getClientId());
            
            ps.executeUpdate();
            message = "Client data updated.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Could not update client data: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public String delete(Client cli) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "delete from client where client_id=?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, cli.getClientId());
            ps.executeUpdate();
            message = "Client file deleted.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to delete client file: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public Client findById(long idClient) {
        Client client = null;
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "select * from client where client_id=? LIMIT 1";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, idClient);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                 long id = rs.getLong("client_id");
                String idCard = rs.getString("id_card");
                String nameCompany = rs.getString("company_name");
                String nameContact = rs.getString("contact_name");
                String address = rs.getString("address");
                String fax = rs.getString("fax");
                String email = rs.getString("email");
                String cellphone = rs.getString("cellphone");
                String phone = rs.getString("phone");
                
                client = new Client(id, idCard, nameCompany, nameContact, address, fax, email, cellphone, phone);
      
            }
           dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
             dataBase.disconnectDB();
        }
        
        return client;
    }
    
}
