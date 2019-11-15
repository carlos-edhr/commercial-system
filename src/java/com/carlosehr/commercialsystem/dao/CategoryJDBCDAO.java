/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.connections.DataBasePG;
import com.carlosehr.commercialsystem.models.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sylvia
 */
public class CategoryJDBCDAO implements ICategoryDAO {
    
    @Override
    public List<Category> listAll() {
        
        Category cat;
        List<Category> listCategory = new ArrayList<>();
        try {
            DataBasePG dataBase = new DataBasePG();
            String sql = "select * from category";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                cat = new Category();
                cat.setCategory_id(rs.getInt("category_id"));
                cat.setName(rs.getString("name"));
                listCategory.add(cat);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error found at listAll - category: " + 
                    ex.getMessage());
        }
        
        return listCategory;
    }

    @Override
    public String insert(Category cat) {
        String message = "";
        try {
            DataBasePG dataBase = new DataBasePG();
            String sql = "insert into category (category_id, name) "
                    + "values(?, ?)";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, cat.getCategory_id());
            ps.setString(2, cat.getName());
            ps.executeUpdate();
            message = "Category has been created.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to create category: " + ex.getMessage();
        }
        return message;
    }

    @Override
    public Category findById(long id) {
        Category cat = null;
        
        try {
            DataBasePG dataBase = new DataBasePG();
            String sql = "select * from category where category_id=? LIMIT 1";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                cat = new Category();
                cat.setCategory_id(rs.getInt("category_id"));
                cat.setName(rs.getString("name"));
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return cat;
    }

    @Override
    public String update(Category cat) {
        String message = "";
        try {
            DataBasePG dataBase = new DataBasePG();
            String sql = "UPDATE category SET name = ? "
                    + "WHERE category_id = ?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setString(1, cat.getName());
            ps.setLong(2, cat.getCategory_id());
            ps.executeUpdate();
            message = "Category has been updated.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to update category: " + ex.getMessage();
        }
        return message;
    }

    @Override
    public String delete(Category cat) {
        String message = "";
        try {
            DataBasePG dataBase = new DataBasePG();
            String sql = "delete from category where category_id=?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, cat.getCategory_id());
            ps.executeUpdate();
            message = "Category deleted.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to delete category: " + ex.getMessage();
        }
        return message;
    }
    
    @Override
    public List<Category> searchByCriteria(String param) {
        DataBasePG dataBase = new DataBasePG();
        Category cat;
        List<Category> listCategory = new ArrayList<>();
        try {
            
            String sql = "select * from category where name ILIKE ?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setString(1, "%"+param+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                cat = new Category();
                cat.setCategory_id(rs.getInt("category_id"));
                cat.setName(rs.getString("name"));
                listCategory.add(cat);
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            System.out.println("Error at searchByCriteria - category: " + 
                    ex.getMessage());
        } finally{
            if (dataBase.getConnection()!=null) {
                dataBase.disconnectDB();
            }
        }
        
        return listCategory;
    }
}