package com.telecom.exceptionhandling;

public class UserPlanNotFound extends RuntimeException {
    public UserPlanNotFound(String message) {
        super(message);
    }

}
