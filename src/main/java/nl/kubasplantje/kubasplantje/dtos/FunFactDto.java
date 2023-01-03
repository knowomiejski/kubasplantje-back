package nl.kubasplantje.kubasplantje.dtos;

public class FunFactDto {

    private Long id;
    private String funfact;

    public FunFactDto() {}

    public FunFactDto(Long id, String funfact) {
        this.id = id;
        this.funfact = funfact;
    }

    public FunFactDto(String funfact) {
        this.funfact = funfact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunfact() {
        return funfact;
    }

    public void setFunfact(String funfact) {
        this.funfact = funfact;
    }

}
