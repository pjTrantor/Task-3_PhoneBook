package com.trantor.phonebookapi.controller;

import com.trantor.phonebookapi.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ExcelController {

    @Autowired
    ExcelService excelService;


    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        excelService.listAll(response);
    }
}
