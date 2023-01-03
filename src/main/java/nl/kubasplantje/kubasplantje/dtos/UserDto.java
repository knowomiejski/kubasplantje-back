package nl.kubasplantje.kubasplantje.dtos;

public class UserDto {

    private Long Id;
    private String userName;

    public UserDto() {}

    public UserDto(Long id, String userName) {
        Id = id;
        this.userName = userName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
