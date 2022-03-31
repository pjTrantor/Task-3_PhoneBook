package com.trantor.phonebookapi.repo;

import com.trantor.phonebookapi.model.ContactModel;
import com.trantor.phonebookapi.model.MobileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MobileRepo extends JpaRepository<MobileModel,Integer> {

}
