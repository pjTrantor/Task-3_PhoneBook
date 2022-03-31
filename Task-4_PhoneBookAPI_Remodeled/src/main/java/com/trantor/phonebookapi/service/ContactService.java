package com.trantor.phonebookapi.service;

import com.trantor.phonebookapi.model.ContactModel;

import java.util.List;

public interface ContactService {

    public List<ContactModel> findAllContact();

    public ContactModel saveContact(ContactModel contact);

    public List<ContactModel> findByfirstName(String name);

    public ContactModel deleteByID(Integer ID);

}
