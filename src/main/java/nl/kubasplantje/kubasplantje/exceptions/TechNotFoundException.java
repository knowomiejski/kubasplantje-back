package nl.kubasplantje.kubasplantje.exceptions;

public class TechNotFoundException extends RuntimeException {
    public TechNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
