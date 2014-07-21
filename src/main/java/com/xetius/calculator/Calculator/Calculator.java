package com.xetius.calculator.Calculator;

import java.io.IOException;

public interface Calculator {
    void setParameters(String[] args);
    long calculate() throws IOException, InvalidStatementsException;
}
