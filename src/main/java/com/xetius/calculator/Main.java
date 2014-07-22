package com.xetius.calculator;

import com.xetius.calculator.calculator.Calculator;
import com.xetius.calculator.calculator.FileProcessingCalculator;
import com.xetius.calculator.exception.InvalidArgumentException;
import com.xetius.calculator.exception.InvalidStatementsException;
import com.xetius.calculator.parser.CalculatingSourceParser;
import com.xetius.calculator.processor.FileSourceProcessor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new FileProcessingCalculator(new FileSourceProcessor(), new CalculatingSourceParser());

        try {
            calculator.setParameters(args);
            long result = calculator.calculate();
            System.out.println(result);
        } catch (IOException | InvalidStatementsException e) {
            System.out.println("ERROR");
        } catch (InvalidArgumentException e) {
            System.out.println("Invalid file name supplied");
        }
    }
}
