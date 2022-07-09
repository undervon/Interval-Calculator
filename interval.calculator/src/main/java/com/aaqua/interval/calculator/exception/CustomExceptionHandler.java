package com.aaqua.interval.calculator.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(IntervalCalculatorNotFoundException.class)
    public ResponseEntity<String> intervalCalculatorNotFoundExceptionHandler(
            IntervalCalculatorNotFoundException intervalCalculatorNotFoundException) {
        log.error("thrown IntervalCalculatorNotFoundException");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("The interval object with the id '%s' does not exist",
                        intervalCalculatorNotFoundException.getMessage()));
    }
}
