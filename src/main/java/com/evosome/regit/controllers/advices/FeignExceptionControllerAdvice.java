package com.evosome.regit.controllers.advices;

import feign.FeignException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FeignExceptionControllerAdvice {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleException(FeignException e) {
        return new ResponseEntity<>(
                HttpStatusCode.valueOf(e.status())
        );
    }

}
