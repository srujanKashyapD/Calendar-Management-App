package com.srujanskd.calendarmanagement.service;

import com.srujanskd.calendarmanagement.model.Employee;
import com.srujanskd.calendarmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    public Employee findById(Long id) {

        Employee fetchedEmp = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found for this id :: " + id));
        return fetchedEmp;
    }
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }
    public void deleteById(Long id) {
        Employee emp = findById(id);

        employeeRepository.delete(emp);
    }
    public void deleteByEmail(String email) {
        Employee emp = findByEmail(email);
        employeeRepository.delete(emp);
    }


    public Employee updateById(Employee employeeDetails, Long id) {
        Employee employee = findById(id);
        employee.setEmail(employeeDetails.getEmail());
        employee.setName(employeeDetails.getName());
        employee.setAddress(employeeDetails.getAddress());
        employee.setOfficeLocation(employeeDetails.getOfficeLocation());
        return save(employee);
    }
    public Employee updateByEmail(Employee employeeDetails, String email) {
        Employee employee = findByEmail(email);
        employee.setId(employeeDetails.getId());
        employee.setName(employeeDetails.getName());
        employee.setAddress(employeeDetails.getAddress());
        employee.setOfficeLocation(employeeDetails.getOfficeLocation());
        return save(employee);
    }

}
