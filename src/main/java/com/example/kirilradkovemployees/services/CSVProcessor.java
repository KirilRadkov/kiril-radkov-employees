package com.example.kirilradkovemployees.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
@Slf4j
public class CSVProcessor {

    public List<EmployeeProject> getCSVEntries(MultipartFile file) {
        return convertToEntityList(file, EmployeeProject.class);
    }

    private static <T> List<T> convertToEntityList(MultipartFile file, Class<T> responseType) {
        List<T> csvEntities;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<?> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(responseType)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(1)
                    .withIgnoreEmptyLine(true)
                    .withSeparator(',')
                    .build();
            csvEntities = (List<T>) csvToBean.parse();
        } catch (Exception ex) {
            log.error("error parsing csv file {} ", ex);
            throw new IllegalArgumentException(ex.getCause().getMessage());
        }
        return csvEntities;
    }
}