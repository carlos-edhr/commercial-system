package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.connections.DataBasePG;
import com.carlosehr.commercialsystem.models.Employee;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Hoyos
 */
public class EmployeeJDBCDAO implements IEmployeeDAO{
@Override
    public List<Employee> listAll() {
        Employee employee;
        List<Employee> listEmployee = new ArrayList<>();
        DataBasePG dataBase = new DataBasePG();
        try {
            String sql = "select * from employee";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String last_name = rs.getString("last_name");
                Date birthDate = rs.getDate("birth_date");
                int reportsTo = rs.getInt("reports_to");
                int ext = rs.getInt("ext");
                
                employee = new Employee(id, name, last_name, birthDate, reportsTo, ext);
                if (reportsTo > 0) {
                    Employee boss = findById(reportsTo);
                    employee.setBoss(boss.getCompleteName());
                }
                
                
                listEmployee.add(employee);
            }
            
            dataBase.disconnectDB();
            
        } catch (SQLException ex) {
            System.out.println("Error at listAll - Employee: " + 
                    ex.getMessage());
            dataBase.disconnectDB();
        }
        
        return listEmployee;
    }

    @Override
    public String insert(Employee emp) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        try {
            
            String sql = "insert into employee (employee_id, name, "
                    + "last_name, birth_date, reports_to, ext) "
                    + "values(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, emp.getEmployee_id());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getLast_name());
            ps.setDate(4, emp.getBirth_date());
            ps.setLong(5, emp.getReports_to());
            ps.setLong(6, emp.getExt());
            ps.executeUpdate();
            message = "Employee file created.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to register employee: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public String update(Employee emp) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "UPDATE employee SET name=?, last_name=?,"
                    + "birth_date=?, reports_to=?, ext=? "
                    + "where employee_id=?";
            PreparedStatement ps = 
                    dataBase.getConnection().prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getLast_name());
            ps.setDate(3, emp.getBirth_date());
            ps.setLong(4, emp.getReports_to());
            ps.setLong(5, emp.getExt());
            ps.setLong(6, emp.getEmployee_id());
            ps.executeUpdate();
            
            message = "Employee file updated.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to update employee file: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public String delete(Employee emp) {
        String message = "";
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "delete from employee where employee_id=?";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, emp.getEmployee_id());
            ps.executeUpdate();
            message = "Employee file deleted.";
            
            dataBase.disconnectDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Unable to delete employee file: " + ex.getMessage();
            dataBase.disconnectDB();
        }
        return message;
    }

    @Override
    public Employee findById(long idEmployee) {
        Employee employee = null;
        DataBasePG dataBase = new DataBasePG();
        
        try {
            String sql = "select * from employee where employee_id=? LIMIT 1";
            PreparedStatement ps = dataBase.getConnection().prepareStatement(sql);
            ps.setLong(1, idEmployee);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String last_name = rs.getString("last_name");
                Date birthDate = rs.getDate("birth_date");
                int reportsTo = rs.getInt("reports_to");
                int ext = rs.getInt("ext");
                
                employee = new Employee(id, name, last_name, birthDate, reportsTo, ext);
                
                if (reportsTo > 0) {
                    Employee boss = findById(reportsTo);
                    employee.setBoss(boss.getCompleteName());
                }
            }
            dataBase.disconnectDB();   
        } catch (SQLException ex) {
            ex.printStackTrace();
            dataBase.disconnectDB();
        }
        return employee;
    }
    
}