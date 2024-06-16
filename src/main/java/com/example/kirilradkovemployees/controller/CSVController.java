package com.example.kirilradkovemployees.controller;

import com.example.kirilradkovemployees.services.ProjectCollaborationAnalyzer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@Controller
@Slf4j
public class CSVController {
    private final ProjectCollaborationAnalyzer projectCollaborationAnalyzer;

    @GetMapping(value = {"/csv"})
    public String getUploadPage() {
        return "upload";
    }

    @PostMapping(value = {"/uploadAndShowResults"})
    public ModelAndView processUploadedCSV(@RequestParam("file") MultipartFile file) {
        log.info("upload file  - file ={}, ", file);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resultPage");
        modelAndView.addObject("employeePairs", projectCollaborationAnalyzer.getResultList(file));

        return modelAndView;
    }
}
