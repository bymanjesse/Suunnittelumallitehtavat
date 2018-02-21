
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.dao;

import com.mycompany.varasto.entity.Employee;
import javafx.collections.ObservableList;

/**
 *
 * @author aleks
 */

// interface Employee modelille

public interface EmployeeDao {
    
    public ObservableList<Employee> getEmployees();
    public Employee getEmployee(long id);
    public String getEmployeeType(String username);
    public void saveEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(Employee employee);
    public boolean checkPassword(String username,String password);
    public boolean checkUser(String username);
}
