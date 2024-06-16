package com.example.kirilradkovemployees.util


import spock.lang.Specification

import java.time.LocalDate

class DateConverterUtilTest extends Specification {

    def dateConverrterUtil = new DateConverterUtil();

    def "convert NULL"() {
        given:
        def string = "NULL"
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.now()
    }

    def "convert null"() {
        given:
        def string = null
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.now()
    }

    def "convert blank"() {
        given:
        def string = ""
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.now()
    }

    def "convert yyyy-M-dd"() {
        given:
        def string = "2024-6-15"
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.of(2024, 06, 15)
    }

    def "convert yyyy-MM-dd"() {
        given:
        def string = "2024-12-15"
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.of(2024, 12, 15)
    }

    def "convert yyyy/MM/dd"() {
        given:
        def string = "2024/12/15"
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.of(2024, 12, 15)
    }

    def "convert MMM dd yyyy"() {
        given:
        def string = "Oct 17 2024"
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.of(2024, 10, 17)
    }

    def "convert dd.MM.yyyy"() {
        given:
        def string = "02.11.2024"
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.of(2024, 11, 02)
    }

    def "convert M/dd/yyyy"() {
        given:
        def string = "6/29/2024"
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.of(2024, 6, 29)
    }

    def "convert M.dd.yyyy"() {
        given:
        def string = "6.29.2024"
        when:
        def result = dateConverrterUtil.convert(string)
        then:
        result == LocalDate.of(2024, 6, 29)
    }

}
