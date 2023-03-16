package com.srujanskd.calendarmanagement.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "employees", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "office_location")
    private String officeLocation;
}
