package com.example.employeepayroll.service;


import com.example.employeepayroll.Exception.EmployeePayrollException;
import com.example.employeepayroll.Repository.IEmployeeRepository;
import com.example.employeepayroll.Util.UToken;
import com.example.employeepayroll.dto.EmployeeDTO;
import com.example.employeepayroll.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    IEmployeeRepository employeeRepository;

    @Autowired
    UToken uToken;

    @Override
    public List<Employee> fetchAllEmployeesData() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee fetchEmployeesDataById(String token) {
        return employeeRepository.findById(uToken.decodeToken(token))
                .orElseThrow(() -> new EmployeePayrollException("Employee With employeeId: " + uToken.decodeToken(token) + " does not exists"));
    }

    @Override
    public Employee addEmployeeData(@RequestBody EmployeeDTO employeeDTO) {
        Employee employeeData = new Employee(employeeDTO);
        return employeeRepository.save(employeeData);
    }

    @Override
    public Employee updateEmployeeData(@RequestHeader String token, @RequestBody EmployeeDTO employeeDTO) {
        Employee employeeData = this.fetchEmployeesDataById(token);
        employeeData.updateEmployeeData(employeeDTO);
        return employeeRepository.save(employeeData);
    }

    @Override
    public void deleteEmployeeDataById(String token) {
        Employee employeeData = this.fetchEmployeesDataById(token);
        employeeRepository.delete(employeeData);
    }
}