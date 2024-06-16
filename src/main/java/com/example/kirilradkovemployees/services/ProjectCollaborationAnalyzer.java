package com.example.kirilradkovemployees.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectCollaborationAnalyzer {

    private final CSVProcessor csvProcessor;

    public List<EmployeePair> getResultList(MultipartFile file){
        var csvEntries = csvProcessor.getCSVEntries(file);
        return findLongestOverlap(csvEntries);

    }

    private static List<EmployeePair> findLongestOverlap(List<EmployeeProject> records) {
        Map<Integer, List<EmployeeProject>> projects = new HashMap<>();

        for (EmployeeProject record : records) {
            projects.computeIfAbsent(record.getProjectId(), k -> new ArrayList<>()).add(record);
        }

        List<EmployeePair> longestOverlaps = new ArrayList<>();

        projects.forEach((pId, employeeProjects) -> {
            long maxOverlap = 0;
                EmployeePair bestPair = null;

                for (int i = 0; i < employeeProjects.size(); i++) {
                    for (int j = i + 1; j < employeeProjects.size(); j++) {
                        EmployeeProject emp1 = employeeProjects.get(i);
                        EmployeeProject emp2 = employeeProjects.get(j);

                        long overlap = calculateOverlap(emp1.getDateFrom(), emp1.getDateTo(), emp2.getDateFrom(), emp2.getDateTo());
                        if (overlap > maxOverlap) {
                            maxOverlap = overlap;
                            bestPair = new EmployeePair(emp1.getEmpId(), emp2.getEmpId(), pId, overlap);
                        }
                    }
                }

                if (bestPair != null) {
                    longestOverlaps.add(bestPair);
                }
        });


        return longestOverlaps;
    }


    private static long calculateOverlap(LocalDate dateFromEmp1, LocalDate DateToEmp1, LocalDate DateFromEmp2, LocalDate DateToEmp2) {
        LocalDate latestStart = dateFromEmp1.isAfter(DateFromEmp2) ? dateFromEmp1 : DateFromEmp2;
        LocalDate earliestEnd = DateToEmp1.isBefore(DateToEmp2) ? DateToEmp1 : DateToEmp2;

        if (latestStart.isAfter(earliestEnd)) {
            return 0;
        }

        return ChronoUnit.DAYS.between(latestStart, earliestEnd) + 1;
    }
}