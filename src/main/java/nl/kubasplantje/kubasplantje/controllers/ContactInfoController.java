package nl.kubasplantje.kubasplantje.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.kubasplantje.kubasplantje.dtos.ContactInfoDto;
import nl.kubasplantje.kubasplantje.models.ContactInfoModel;
import nl.kubasplantje.kubasplantje.services.ContactInfoService;

@RestController
@RequestMapping(path = "api/v1/contactinfo")
public class ContactInfoController {

    @Autowired
    private ModelMapper modelMapper;
    private ContactInfoService contactInfoService;

    @Autowired
    public ContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    @GetMapping
    public ResponseEntity<List<ContactInfoDto>> getAllContactInfos() {
        List<ContactInfoModel> contactInfoModels = this.contactInfoService.getAllContactInfos();
        List<ContactInfoDto> contactInfoDtos = new ArrayList<>();
        for (ContactInfoModel contactInfoModel : contactInfoModels) {
            contactInfoDtos.add(convertContactInfoModelToContactInfoDto(contactInfoModel));
        }
        return ResponseEntity.ok(contactInfoDtos);
    }

    @PostMapping
    public ResponseEntity<ContactInfoDto> addNewContactInfo(@RequestBody ContactInfoDto contactInfoDto) {
        ContactInfoModel contactInfoModel = convertContactInfoDtoToContactInfoModel(contactInfoDto);
        ContactInfoDto addedContactInfo = convertContactInfoModelToContactInfoDto(this.contactInfoService.addNewContactInfo(contactInfoModel));
        return ResponseEntity.ok(addedContactInfo);
    }

    @PatchMapping
    public ResponseEntity<ContactInfoDto> updateContactInfo(@RequestBody ContactInfoDto contactInfoDto) {
        ContactInfoModel contactInfoModel = convertContactInfoDtoToContactInfoModel(contactInfoDto);
        ContactInfoDto updatedContactInfo = convertContactInfoModelToContactInfoDto(this.contactInfoService.updateContactInfo(contactInfoModel));
        return ResponseEntity.ok(updatedContactInfo);
    }

    private ContactInfoDto convertContactInfoModelToContactInfoDto(ContactInfoModel contactInfoModel) {
        return this.modelMapper.map(contactInfoModel, ContactInfoDto.class);
    }

    private ContactInfoModel convertContactInfoDtoToContactInfoModel(ContactInfoDto contactInfoDto) {
        return this.modelMapper.map(contactInfoDto, ContactInfoModel.class);
    }
}
