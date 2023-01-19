package nl.kubasplantje.kubasplantje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.kubasplantje.kubasplantje.models.ContactInfoModel;
import nl.kubasplantje.kubasplantje.repositories.ContactInfoRepository;

@Service
public class ContactInfoService {

    private ContactInfoRepository contactInfoRepository;

    @Autowired
    public ContactInfoService(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    public List<ContactInfoModel> getAllContactInfos() {
        return this.contactInfoRepository.findAll();
    }

    public ContactInfoModel addNewContactInfo(ContactInfoModel contactInfoModel) {
        return this.contactInfoRepository.save(contactInfoModel);
    }

    public ContactInfoModel updateContactInfo(ContactInfoModel contactInfoModel) {
        ContactInfoModel contactInfo = this.contactInfoRepository.findById(contactInfoModel.getId()).orElseThrow();
        contactInfo.setCompanyEmail(contactInfoModel.getCompanyEmail());
        contactInfo.setPersonalEmail(contactInfoModel.getPersonalEmail());
        contactInfo.setPhoneNumber(contactInfoModel.getPhoneNumber());
        return this.contactInfoRepository.save(contactInfo);
    }
}
