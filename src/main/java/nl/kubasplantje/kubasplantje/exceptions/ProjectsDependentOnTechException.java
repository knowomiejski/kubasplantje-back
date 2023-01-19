package nl.kubasplantje.kubasplantje.exceptions;

public class ProjectsDependentOnTechException extends RuntimeException {
    public ProjectsDependentOnTechException (String errorMessage) {
        super(errorMessage);
    }
}
