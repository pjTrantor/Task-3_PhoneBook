package com.trantor.phonebookapi.model;

import com.sun.istack.NotNull;
import org.aspectj.bridge.Message;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Contact")
@EntityListeners(AuditingEntityListener.class)
public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull()
    private Integer contactId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @LastModifiedBy
    private String updatedBy;
    @UpdateTimestamp
    private Date updatedDate;
    private Boolean isActive;

    @OneToMany(targetEntity = MobileModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_Id", referencedColumnName = "contactId")
    private List<MobileModel> mobile;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }



    public List<MobileModel> getMobile() {
        return mobile;
    }

    public void setMobile(List<MobileModel> mobile) {
        this.mobile = mobile;
    }

    public ContactModel(Integer contactId, String firstName, String lastName, String emailAddress, String createdBy, Date createdDate, String updatedBy, Date updatedDate, Boolean isActive, List<MobileModel> mobile) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.isActive = isActive;
        this.mobile = mobile;
    }

    public ContactModel() {
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedDate=" + updatedDate +
                ", isActive='" + isActive + '\'' +
                ", mobile=" + mobile +
                '}';
    }
}
