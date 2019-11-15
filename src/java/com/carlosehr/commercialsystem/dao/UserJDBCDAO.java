
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.connections.DataBasePG;
import com.carlosehr.commercialsystem.exception.ErrorRegisterException;
import com.carlosehr.commercialsystem.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

public class UserJDBCDAO implements IUserDAO {

    @Override
    public User validateUser(String userString, String pass) {
        User user = null;

        DataBasePG dataBase = new DataBasePG();
        try {
            String sql = "select * from user where user = ? AND password = ? LIMIT 1;";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setString(1, userString);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                user = new User();
                long id = rs.getLong("id");
                String nameUser = rs.getString("user");
                String password = rs.getString("password");
                
                user.setUserId(id);
                user.setUser(nameUser);
                user.setPassword(password);
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            System.out.println("Error - could not get user: ");
            ex.printStackTrace();
        }finally{
            if (dataBase.getConnection()!=null) {
                dataBase.disconnectDB();
            }
        }
        
        return user;
    }

    @Override
    public User createUser(User user) throws ErrorRegisterException {
          DataBasePG dataBase = new DataBasePG();
        try {
            
            String sql = "insert into user(user, password) "
                    + "values(?, ?)";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            
            int idGenerated;
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerated = generatedKeys.getInt(1);
                user.setUserId(idGenerated);
            }
            
            dataBase.disconnectDB();

        } catch(PSQLException excepcionPSQL){
            if (excepcionPSQL.getSQLState().equals("23505")) {
                throw new ErrorRegisterException("This email address already has an account.");
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            if (dataBase.getConnection()!=null) {
                dataBase.disconnectDB();
            }
        }
        return user;
    }
    
}

