package com.xetius.calculator.calculator;

import com.xetius.calculator.exception.InvalidArgumentException;
import com.xetius.calculator.exception.InvalidStatementsException;

import java.io.IOException;

public interface Calculator {
    void setParameters(String[] args) throws InvalidArgumentException;
    long calculate() throws IOException, InvalidStatementsException;
}
