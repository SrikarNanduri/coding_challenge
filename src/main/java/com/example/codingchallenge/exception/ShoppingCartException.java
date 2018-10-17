package com.example.codingchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartException extends Exception {

    public ShoppingCartException(String message){
      super(message);

    }
}
