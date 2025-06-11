package com.workintech.zoo.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;


public class ZooException extends RuntimeException{
    HttpStatus status;

    public Integer getHttpStatus() {
        return status.value();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ZooException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.status = httpStatus;
    }
}
