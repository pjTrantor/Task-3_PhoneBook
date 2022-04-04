package com.trantor.phonebookapi.controller;

import com.trantor.phonebookapi.dto.ContactDTO;
import com.trantor.phonebookapi.service.extraservice.ExternalApiConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RemoteContactController {

    @Autowired
    ExternalApiConnector externalApiConnector;

    @GetMapping("/getExternalData")
    public List<ContactDTO> getExternalData(){
        return externalApiConnector.getAllProducts();
    }

    @PostMapping("/saveExternalData")
    public ResponseEntity<ContactDTO> saveExternalData(@RequestBody ContactDTO contactDTO){
        return externalApiConnector.createProducts(contactDTO);
    }

    @DeleteMapping("deleteExternalData")
    public ContactDTO deleteExternalData(Integer Id){
        return externalApiConnector.deleteProduct(Id);
    }

    @GetMapping("/getExternalByName")
    public List<ContactDTO> getExternalDataByName(String name) {
    return externalApiConnector.getByName(name);
    }
}
