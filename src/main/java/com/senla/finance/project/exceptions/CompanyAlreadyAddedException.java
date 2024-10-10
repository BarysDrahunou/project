package com.senla.finance.project.exceptions;

public class CompanyAlreadyAddedException extends RuntimeException{
    public CompanyAlreadyAddedException(String message) {
        super(message);
    }
}
