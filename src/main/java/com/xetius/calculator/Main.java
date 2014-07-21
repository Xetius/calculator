package com.xetius.calculator;

import com.xetius.calculator.Calculator.Calculator;
import com.xetius.calculator.Calculator.FileProcessingCalculator;
import com.xetius.calculator.Calculator.InvalidStatementsException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new FileProcessingCalculator();
        calculator.setParameters(args);
        try {
            long result = calculator.calculate();
            System.out.println(result);
        } catch (IOException | InvalidStatementsException e) {
            System.out.println("ERROR");
        }
    }
}
