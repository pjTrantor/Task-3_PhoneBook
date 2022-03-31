package com.trantor.phonebookapi.controller;

import com.trantor.phonebookapi.dto.ContactDTO;
import com.trantor.phonebookapi.model.ContactModel;
import com.trantor.phonebookapi.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/API")
public class ContactController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<ContactDTO> findAll(){
        return contactService.findAllContact().stream().map(contact -> modelMapper.map(contact,ContactDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ContactDTO saveData(@RequestBody ContactDTO contactDTO){

        System.out.println(contactDTO.getIsActive());
        ContactModel contactModel = modelMapper.map(contactDTO, ContactModel.class);
        contactService.saveContact(contactModel);

        return contactDTO;
    }

    @GetMapping("/byName/{name}")
    public List<ContactDTO> findByName(@PathVariable("name") String name){
//        List<ContactModel> byfirstName = contactService.findByfirstName(name);
//
//        List<ContactDTO> contactDTOS = new ArrayList<ContactDTO>();
//
//        for (ContactModel contactModel : byfirstName) {
//            ContactDTO contactDTO = modelMapper.map(contactModel, ContactDTO.class);
//            contactDTOS.add(contactDTO);
//        }
//
//        return contactDTOS;

        return contactService.findByfirstName(name).stream().map(contact -> modelMapper.map(contact,ContactDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping
    public ContactModel deleteByID(Integer ID){
        return contactService.deleteByID(ID);
    }
}
