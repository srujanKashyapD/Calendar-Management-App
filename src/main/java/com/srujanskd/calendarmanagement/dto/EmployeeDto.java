package com.srujanskd.calendarmanagement.dto;

import lombok.Data;


@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String officeLocation;
}
