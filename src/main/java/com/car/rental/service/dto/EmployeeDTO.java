package com.car.rental.service.dto;

import com.car.rental.service.dao.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String name;
    private String role;

    public static EmployeeDTO toDto(Employee employee){
        EmployeeDTO employeeDto= EmployeeDTO.builder()
                .email(employee.getEmail())
                .name(employee.getName())
                .password(employee.getPassword())
                .username(employee.getUsername())
                .role(employee.getRole().getRoleId())
                .build();
        return employeeDto;
    }
    public static Employee toEntity (EmployeeDTO employeeDTO){
        Employee employee=Employee.builder()
                .email(employeeDTO.getEmail())
                .username(employeeDTO.getUsername())
                .name(employeeDTO.getName())
                .password(employeeDTO.getPassword())
                .build();
        return employee;
    }
    public List<EmployeeDTO> toDtoList(List<Employee>employees){
        return employees.stream().map(EmployeeDTO::toDto).toList();
    }
    public List<Employee>toEntityList(List<EmployeeDTO>employeeDTOS){
        return employeeDTOS.stream().map(EmployeeDTO::toEntity).toList();
    }
}
