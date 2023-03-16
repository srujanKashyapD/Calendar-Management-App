package com.srujanskd.calendarmanagement.controller;


import com.srujanskd.calendarmanagement.dto.EmployeeDto;
import com.srujanskd.calendarmanagement.model.Employee;
import com.srujanskd.calendarmanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController

@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        try {
            List<EmployeeDto> allEmp = employeeService.findAll().stream()
                    .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(allEmp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam(name = "id", required = false) Long id,
                                                @RequestParam(name = "email", required = false) String email){
        if(id != null) {
            EmployeeDto emp = modelMapper.map(employeeService.findById(id), EmployeeDto.class);
            return ResponseEntity.ok().body(emp);
        }
        else if(email != null) {
            EmployeeDto emp = modelMapper.map(employeeService.findByEmail(email), EmployeeDto.class);
            return ResponseEntity.ok().body(emp);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);
            Employee newEmployee = employeeService
                    .save(employeeRequest);
            EmployeeDto employeeResponse = modelMapper.map(newEmployee, EmployeeDto.class);
            return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/employee")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam(name = "id", required = false) Long id,
                                                   @RequestParam(name = "email", required = false) String email,
                                                   @RequestBody EmployeeDto employeeDetails) {
        Employee employeeRequest = modelMapper.map(employeeDetails, Employee.class);
        if (id != null) {
            final Employee updatedEmployee = employeeService.updateById(employeeRequest, id);
            final EmployeeDto employeeResponse = modelMapper.map(updatedEmployee, EmployeeDto.class);
            return ResponseEntity.ok(employeeResponse);
        } else if (email != null) {
            final Employee updatedEmployee = employeeService.updateByEmail(employeeRequest, email);
            final EmployeeDto employeeResponse = modelMapper.map(updatedEmployee, EmployeeDto.class);
            return ResponseEntity.ok(employeeResponse);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/employee")
    public Map<String, Boolean> deleteEmployee(@RequestParam(name = "id", required = false) Long id,
                                               @RequestParam(name = "email", required = false)String email) {
        if(id != null) {
            employeeService.deleteById(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }
        else if(email != null) {
            employeeService.deleteByEmail(email);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }
        return (Map<String, Boolean>) ResponseEntity.badRequest();
    }

}
