package io.github.samirsales;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandleValidationExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(
            MethodArgumentNotValidException methodArgumentNotValidException){

        List<String> errorList = methodArgumentNotValidException
            .getBindingResult()
            .getAllErrors()
            .stream()
            .map(ObjectError::getObjectName)
            .collect(Collectors.toList());

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }
}
