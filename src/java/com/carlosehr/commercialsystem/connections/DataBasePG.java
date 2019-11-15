/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sylvia
 */
public class DataBasePG {
 
    Connection conn = null;
    
    public DataBasePG(){
        
        String urlDatabase = "jdbc:postgresql://ec2-54-235-180-123.compute-1.amazonaws.com:5432/dfptvmkvk0irls";
        
    
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase, "wibbzbmivherkz", "8a7c886dc53e711019f785958e950b952fad1676cda34bec14a463bd10679d17");
            
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println("Driver not found: " + ex.getMessage());
        }
    
    }
    
    public Connection getConnection(){
        return this.conn;
    }
    
    public void disconnectDB(){
        System.out.println("Close database connection");
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Unable to connect: " + ex.getMessage());
            }
        }
    }    
}

