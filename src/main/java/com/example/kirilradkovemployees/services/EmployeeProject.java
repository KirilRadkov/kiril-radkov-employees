package com.example.kirilradkovemployees.services;

import com.example.kirilradkovemployees.util.DateConverterUtil;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProject {
    @CsvBindByPosition(position=0)
    private Long empId;
    @CsvBindByPosition(position=1)
    private Integer  projectId;
    @CsvCustomBindByPosition(position = 2, converter = DateConverterUtil.class)
    private LocalDate dateFrom;
    @CsvCustomBindByPosition(position = 3, converter = DateConverterUtil.class)
    private LocalDate dateTo;

}