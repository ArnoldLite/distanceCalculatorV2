package distance_calculator_web_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Error> handleException(BadRequestException e){

        Error error = new Error();
        error.setInfo(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleException(Exception e){

        Error error = new Error();
        error.setInfo(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleException(NotObjsInDatabase e){

        Error error = new Error();
        error.setInfo(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
