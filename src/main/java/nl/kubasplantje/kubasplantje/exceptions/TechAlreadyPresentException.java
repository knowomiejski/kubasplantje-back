package nl.kubasplantje.kubasplantje.exceptions;

public class TechAlreadyPresentException extends RuntimeException {
    public TechAlreadyPresentException(String errorMessage) {
        super(errorMessage);
    }
}

