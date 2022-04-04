package com.trantor.phonebookapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trantor.phonebookapi.dto.ContactDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flag")
public class TargetHitAPIController {

    @Autowired
    private ContactController contactController;
    @Autowired
    private RemoteContactController remoteContactController;

    @Autowired
    private ModelMapper modelMapper;

    ObjectMapper objectMapper =  new ObjectMapper();

    @PostMapping("/{flag}")
    public ResponseEntity<ContactDTO> post(@PathVariable(name = "flag") Integer flag, @RequestBody ContactDTO contactDTO) throws JSONException {

        // for my system
        if(flag == 1){
            ResponseEntity<ContactDTO> contactDTOResponseEntity = contactController.saveData(contactDTO);
            return contactDTOResponseEntity;
        }
        //for another api
        else
        {
            return remoteContactController.saveExternalData(contactDTO);
        }
    }

    @GetMapping("/{flag}")
    public List<ContactDTO> get(@PathVariable(name = "flag") Integer flag) throws JsonProcessingException {
        // for my system
        if (flag == 1) {
            return contactController.findAll();
        }
        //for another api
        else {
            return remoteContactController.getExternalData();
        }
    }

    @DeleteMapping("delete/{flag}/{id}")
    public ContactDTO delete(@PathVariable(name = "id") Integer ID, @PathVariable(name = "flag") Integer flag){
        // for my system
        if (flag == 1) {
            return contactController.deleteByID(ID);
        }
        //for another api
        else {
            return remoteContactController.deleteExternalData(ID);
        }
    }

    @GetMapping("/byName/{flag}/{name}")
    public List<ContactDTO> getByName(@PathVariable(name = "flag") Integer flag,@PathVariable(name = "name") String name) throws JsonProcessingException {
        // for my system
        if (flag == 1) {
            return contactController.getByName(name);
        }
        //for another api
        else {
            return remoteContactController.getExternalDataByName(name);
        }
    }
}
