package com.trantor.phonebookapi.service.impl;

import com.trantor.phonebookapi.exception.ResourceNotFoundException;
import com.trantor.phonebookapi.model.ContactModel;
import com.trantor.phonebookapi.repo.ContactRepo;
import com.trantor.phonebookapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public List<ContactModel> findAllContact() {
        return contactRepo.findAll();
    }

    @Override
    public ContactModel saveContact(ContactModel contact) {
        return contactRepo.save(contact);
    }

    @Override
    public List<ContactModel> findByfirstName(String name) {
        return contactRepo.findByfirstName(name);
    }

    @Override
    public ContactModel deleteByID(Integer ID) {

        ContactModel contactModel = contactRepo.findById(ID).orElseThrow(()
                -> new ResourceNotFoundException("____ * * Value Not FOUND * * ____"));

        contactModel.setIsActive(false);
        return contactRepo.save(contactModel);
    }


}
