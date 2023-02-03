package com.example.rig.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> exceptionHandler(Exception e){

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        BaseException baseException = new BaseException(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(baseException, httpStatus);
    }
}
