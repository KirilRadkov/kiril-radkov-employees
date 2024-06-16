package com.example.kirilradkovemployees.util;

import com.opencsv.bean.AbstractBeanField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;

@Slf4j
public class DateConverterUtil extends AbstractBeanField {

    @Override
    protected LocalDate convert(String s) {
        if (s == null || s.equals("NULL") || s.isBlank()) {
            return LocalDate.now();
        }

        String[] patterns = {"yyyy-MM-dd", "dd-MM-yyyy", "yyyy-M-dd" ,  "yyyy.M.dd","yyyy.MM.dd", "dd.MM.yyyy", "M/d/yyyy", "MM/dd/yyyy", "dd/MM/yyyy", "MMM dd yyyy", "M/dd/yyyy", "M.dd.yyyy", "MM/dd/yy", "yyyy/MM/dd"};

        try {
           return DateUtils.parseDateStrictly(s ,patterns).toInstant().atZone(ZoneId.systemDefault())
                   .toLocalDate();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
