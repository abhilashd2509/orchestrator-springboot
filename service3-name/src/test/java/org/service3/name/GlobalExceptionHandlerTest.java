package org.service3.name;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.service3.name.dto.NameRequest;
import com.service3.name.exception.GlobalExceptionHandler;
import com.service3.name.exception.InvalidNameException;

import java.util.Map;


class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleValidationExceptions_shouldReturnFieldErrors() {
        // Create a mock MethodArgumentNotValidException
        NameRequest request = new NameRequest("", "");
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(request, "nameRequest");
        result.addError(new FieldError("nameRequest", "name", "Name must not be blank"));
        result.addError(new FieldError("nameRequest", "surname", "Surname must not be blank"));
        
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, result);
        
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleValidationExceptions(ex);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Name must not be blank", response.getBody().get("name"));
        assertEquals("Surname must not be blank", response.getBody().get("surname"));
    }

    @Test
    void handleInvalidName_shouldReturnBadRequest() {
        InvalidNameException ex = new InvalidNameException("Test error message");
        
        ResponseEntity<String> response = exceptionHandler.handleInvalidName(ex);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Test error message", response.getBody());
    }
}