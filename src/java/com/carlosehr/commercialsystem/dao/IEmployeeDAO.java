/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.dao;

import com.carlosehr.commercialsystem.models.Employee;
import java.util.List;

/**
 *
 * @author Sylvia
 */
public interface IEmployeeDAO {
    public List<Employee> listAll();
    public String insert(Employee emp);
    public String update(Employee emp);
    public String delete(Employee emp);
    public Employee findById(long idEmployee);
    
    
}
