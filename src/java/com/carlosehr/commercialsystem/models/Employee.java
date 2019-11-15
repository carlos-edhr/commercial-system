/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.models;

import java.sql.Date;

/**
 *
 * @author Sylvia
 */
public class Employee {
   private long employee_id;
   private String name;
   private String last_name;
   private Date birth_date;
   private long reports_to;
   private long ext;
   private String boss;

    public Employee() {
    }

    public Employee(long employee_id) {
        this.employee_id = employee_id;
    }

    public Employee(long employee_id, String name, String last_name, Date birth_date, long reports_to, long ext) {
        this.employee_id = employee_id;
        this.name = name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.reports_to = reports_to;
        this.ext = ext;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public long getReports_to() {
        return reports_to;
    }

    public void setReports_to(long reports_to) {
        this.reports_to = reports_to;
    }

    public long getExt() {
        return ext;
    }

    public void setExt(long ext) {
        this.ext = ext;
    }
    
    public String getCompleteName() {
        return name + " " + last_name;
    }
    
    public void setBoss(String name){
        this.boss = name;
    }
    
    public String getBoss(){
        return this.boss;
    }
   
   
    
}
