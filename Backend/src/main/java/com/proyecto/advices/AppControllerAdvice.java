package com.proyecto.advices;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.proyecto.dto.ErrorDTO;


@ControllerAdvice
public class AppControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDTO> notFound(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
    }
}
