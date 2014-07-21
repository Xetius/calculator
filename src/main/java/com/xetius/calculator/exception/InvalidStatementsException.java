package com.xetius.calculator.exception;

public class InvalidStatementsException extends Exception {
    public static final String NO_STATEMENTS_PROVIDED = "No statements provided";
    public static final String INVALID_STATEMENTS_PROVIDED = "Invalid statements provided";

    public InvalidStatementsException(String message) {
        super(message);
    }
}
