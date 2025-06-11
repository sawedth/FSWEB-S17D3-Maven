package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException){
        ZooErrorResponse zooErrorResponse =
                new ZooErrorResponse(zooException.getHttpStatus(), zooException.getMessage(),
                        System.currentTimeMillis());
        return new ResponseEntity<>(zooErrorResponse, zooException.getStatus());
    }
    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception){
        ZooErrorResponse zooErrorResponse =
                new ZooErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(),
                        System.currentTimeMillis());
        return new ResponseEntity<>(zooErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
