package org.roadservice.api.trafficsignal.exceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
