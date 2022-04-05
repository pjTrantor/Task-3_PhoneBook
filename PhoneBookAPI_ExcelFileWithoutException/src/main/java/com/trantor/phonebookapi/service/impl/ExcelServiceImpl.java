package com.trantor.phonebookapi.service.impl;

import com.trantor.phonebookapi.model.ContactModel;
import com.trantor.phonebookapi.repo.ContactRepo;
import com.trantor.phonebookapi.service.ExcelService;
import com.trantor.phonebookapi.service.extraservice.UserExcelExporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private UserExcelExporterService userExcelExporterService;

    @Autowired
    private ContactRepo contactRepo;

    public void exportData(ResponseEntity responseEntity) throws IOException {
        userExcelExporterService.export((HttpServletResponse) responseEntity);
    }

    public List<ContactModel> listAll(HttpServletResponse response) {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Contacts_" + "DataExcel" + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ContactModel> all = contactRepo.findAll();

        UserExcelExporterService excelExporter = new UserExcelExporterService(all);

        try {
            excelExporter.export(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return all;
    }

}
