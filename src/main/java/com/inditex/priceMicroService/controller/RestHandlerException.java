package com.inditex.priceMicroService.controller;

import com.inditex.priceMicroService.exception.PriceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ PriceNotFoundException.class })
    public ResponseEntity<Object> handleAccessDeniedException( Exception ex, WebRequest request) {
        final String error = "Application error handling";
        log.error(error, ex);
        return new ResponseEntity<Object>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}