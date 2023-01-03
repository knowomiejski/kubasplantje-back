package nl.kubasplantje.kubasplantje.dtos;

public class ContactInfoDto {

    private Long id;
    private String personalEmail;
    private String companyEmail;
    private String phoneNumber;

    public ContactInfoDto() {}

    public ContactInfoDto(Long id, String personalEmail, String companyEmail, String phoneNumber) {
        this.id = id;
        this.personalEmail = personalEmail;
        this.companyEmail = companyEmail;
        this.phoneNumber = phoneNumber;
    }

    public ContactInfoDto(String personalEmail, String companyEmail, String phoneNumber) {
        this.personalEmail = personalEmail;
        this.companyEmail = companyEmail;
        this.phoneNumber = phoneNumber;
    }

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
