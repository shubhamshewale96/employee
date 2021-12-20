package com.example.employeepayroll.controller;

import com.example.employeepayroll.Util.UToken;
import com.example.employeepayroll.dto.EmployeeDTO;
import com.example.employeepayroll.dto.ResponseDTO;
import com.example.employeepayroll.model.Employee;
import com.example.employeepayroll.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/home")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private UToken uToken;

    @GetMapping(value = {"", "/", "/greet"})
    public String greeting() {
        return "Welcome To Employee Payroll Application.";
    }

    @GetMapping("/employees/all")
    public List<Employee> fetchAllEmployeesData() {
        List<Employee> employeesDataList = employeeService.fetchAllEmployeesData();
        return employeesDataList;
    }

    @GetMapping("/employees")
    public ResponseEntity<ResponseDTO> fetchEmployeesDataById(@RequestHeader String token) {
        Employee employeesData = employeeService.fetchEmployeesDataById(token);
        ResponseDTO responseDTO = new ResponseDTO("Get Employee Payroll Data For ID Successful", employeesData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<ResponseDTO> addEmployeeData(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employeeData = employeeService.addEmployeeData(employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added Employee Payroll Data Successful", employeeData, uToken.createToken(employeeData.getId()));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<ResponseDTO> updateEmployeeDataById(@RequestHeader String token, @Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employeeData = employeeService.updateEmployeeData(token, employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Employee Payroll Data Successful", employeeData, token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<String> deleteEmployeeDataById(@RequestHeader String token) {
        employeeService.deleteEmployeeDataById(token);
        return new ResponseEntity<String>("Deleted Employee Payroll Data Successful Deleted Id: " + uToken.decodeToken(token), HttpStatus.OK);
    }
}