package com.example.kirilradkovemployees.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeePair {
    private Long empId1;
    private Long empId2;
    private Integer projectId;
    private Long daysWorked;
}
