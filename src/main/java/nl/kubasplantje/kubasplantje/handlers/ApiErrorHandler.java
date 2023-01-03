package nl.kubasplantje.kubasplantje.handlers;

import java.security.DrbgParameters.Reseed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import nl.kubasplantje.kubasplantje.exceptions.TechAlreadyPresentException;
import nl.kubasplantje.kubasplantje.exceptions.TechNotFoundException;

@ControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler(TechAlreadyPresentException.class)
    public ResponseEntity<Object> handleTechAlreadyPresentException(TechAlreadyPresentException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TechNotFoundException.class)
    public ResponseEntity<Object> handleTechNotFoundException(TechNotFoundException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
