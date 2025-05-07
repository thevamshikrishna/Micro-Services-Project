package com.telecom.exceptionhandling;

public class PlanNotFound extends RuntimeException {
    public PlanNotFound(String message) {
        super(message);
    }
}
