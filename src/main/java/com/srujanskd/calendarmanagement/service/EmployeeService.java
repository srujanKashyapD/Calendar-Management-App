package com.srujanskd.calendarmanagement.service;

import com.srujanskd.calendarmanagement.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(Long id);
    Employee findByEmail(String email);
    Employee save(Employee employee);
    void deleteById(Long id);
    void deleteByEmail(String email);
    Employee updateById(Employee employeeDetails, Long id);

    Employee updateByEmail(Employee employeeDetails, String email);

}
