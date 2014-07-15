package com.xetius.calculator;

import com.xetius.calculator.Calculator.Calculator;
import com.xetius.calculator.Calculator.FileProcessingCalculator;

public class Main {
    public static void main (String[] args) {
        Calculator calculator = new FileProcessingCalculator();
        calculator.setArguments(args);
        String result = calculator.calculate();
        System.out.println(result);
    }
}
