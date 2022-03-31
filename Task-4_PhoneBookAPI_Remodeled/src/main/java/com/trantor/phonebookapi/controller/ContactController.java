package com.trantor.phonebookapi.controller;

import com.trantor.phonebookapi.model.ContactModel;
import com.trantor.phonebookapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<ContactModel> findAll(){
        return contactService.findAllContact();
    }

    @PostMapping
    public ContactModel saveData(@RequestBody ContactModel contactModel){

        return contactService.saveContact(contactModel);
    }

    @GetMapping("/byName/{name}")
    public List<ContactModel> findByName(String name){
        return contactService.findByfirstName(name);
    }

    @DeleteMapping
    public ContactModel deleteByID(Integer ID){
        return contactService.deleteByID(ID);
    }
}
