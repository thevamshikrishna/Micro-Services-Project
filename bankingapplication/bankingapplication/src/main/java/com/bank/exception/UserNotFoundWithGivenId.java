package com.bank.exception;

public class UserNotFoundWithGivenId extends RuntimeException{
    public UserNotFoundWithGivenId(String message) {
        super(message);
    }
}
