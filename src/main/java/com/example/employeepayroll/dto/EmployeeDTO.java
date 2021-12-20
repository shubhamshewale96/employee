package com.example.employeepayroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public @ToString class EmployeeDTO {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee Name Invalid")
    @NotEmpty(message = "Employee Name Cannot Be Null")
    public String name;

    @Min(value = 10000, message = "Minimum Wage Should Be More Than 10 Thousand")
    public long salary;

    @Pattern(regexp = "male|female")
    public String gender;

    @JsonFormat(pattern = "dd MMM yyyy")
    @NotNull(message = "Start Date Should Not Be Empty")
    @PastOrPresent(message = "Start Date Should Be Past Or Today")
    public LocalDate startDate;

    @NotBlank(message = "Note Cannot Be Empty")
    public String note;

    @NotBlank(message = "Profile Pic Cannot Be Empty")
    public String profilePic;

    @NotNull(message = "Department Should Not Be Empty")
    public List<String> departments;

}