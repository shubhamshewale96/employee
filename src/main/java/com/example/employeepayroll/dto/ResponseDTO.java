package com.example.employeepayroll.dto;

import com.example.employeepayroll.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ResponseDTO {
    private String message;
    private Object data;
    private Object token;

    public ResponseDTO(String message, String s) {}

    public ResponseDTO(String message, List<String> errorMsg) {}

    public ResponseDTO(String s, Employee employeesData) {
    }
}