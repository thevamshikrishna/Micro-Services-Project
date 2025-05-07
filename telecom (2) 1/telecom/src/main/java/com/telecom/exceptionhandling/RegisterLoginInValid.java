package com.telecom.exceptionhandling;

public class RegisterLoginInValid extends RuntimeException {
    public RegisterLoginInValid(String message) {
        super(message);
    }

}
