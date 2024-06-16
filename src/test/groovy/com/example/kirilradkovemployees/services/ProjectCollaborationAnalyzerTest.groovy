package com.example.kirilradkovemployees.services

import org.springframework.mock.web.MockMultipartFile
import spock.lang.Specification

import java.time.LocalDate

class ProjectCollaborationAnalyzerTest extends Specification {

    def csvProcessor = Mock(CSVProcessor)
    def projectCollabAnalyzer = new ProjectCollaborationAnalyzer(csvProcessor)

    def "getResultList"() {
        given:

        MockMultipartFile csvFile = new MockMultipartFile("employees.csv", "", "multipart/form-data", this.getClass().getClassLoader().getResource("employees.csv").getBytes());
        csvProcessor.getCSVEntries(csvFile) >> List.of(EmployeeProject
                .builder()
                .empId(218)
                .projectId(10)
                .dateFrom(LocalDate.parse("2011-12-01"))
                .dateTo(LocalDate.parse("2024-06-15"))
                .build(),
                EmployeeProject
                        .builder()
                        .empId(143)
                        .projectId(10)
                        .dateFrom(LocalDate.parse("2009-01-03"))
                        .dateTo(LocalDate.parse("2011-12-05"))
                        .build())


        when:
        def result = projectCollabAnalyzer.getResultList(csvFile)

        then:
        result.size() == 1
        result.get(0).empId1 == 218
        result.get(0).empId2 == 143
        result.get(0).projectId == 10
        result.get(0).daysWorked == 5
    }

}
