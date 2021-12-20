package com.example.employeepayroll.service;

import com.example.employeepayroll.dto.EmployeeDTO;
import com.example.employeepayroll.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> fetchAllEmployeesData();
    Employee fetchEmployeesDataById(String token);
    Employee addEmployeeData(EmployeeDTO employeeDTO);
    Employee updateEmployeeData(String token, EmployeeDTO employeeDTO);
    void deleteEmployeeDataById(String id);
}
