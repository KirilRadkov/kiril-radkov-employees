package com.example.kirilradkovemployees.services

import org.springframework.mock.web.MockMultipartFile
import spock.lang.Specification

import java.time.LocalDate

class CSVProcessorTest extends Specification {

    def csvProcessor = new CSVProcessor()

    def "convertToEntityList"() {
        given:

        MockMultipartFile csvFile = new MockMultipartFile("employees.csv", "", "multipart/form-data", this.getClass().getClassLoader()
                .getResourceAsStream("employees.csv").getBytes());

        when:
        def result = csvProcessor.getCSVEntries(csvFile)

        then:
        result.size() == 2
        result.get(0).empId == 218
        result.get(0).projectId == 10
        result.get(0).dateFrom == LocalDate.parse("2011-12-01")
        result.get(0).dateTo == LocalDate.parse("2024-06-15")
        result.get(1).empId == 143
        result.get(1).projectId == 10
        result.get(1).dateFrom == LocalDate.parse("2009-01-03")
        result.get(1).dateTo == LocalDate.parse("2011-12-05")
    }

}
