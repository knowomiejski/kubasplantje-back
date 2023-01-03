package nl.kubasplantje.kubasplantje.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "ContactInfo")
@Table(name = "contact_info")
public class ContactInfoModel {

    @Id
    @SequenceGenerator(name="contact_info_id_seq_gen",
                       sequenceName="contact_info_id_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="contact_info_id_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String personalEmail;

    @Column(nullable = false)
    private String companyEmail;

    @Column(nullable = false)
    private String phoneNumber;

    public ContactInfoModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
