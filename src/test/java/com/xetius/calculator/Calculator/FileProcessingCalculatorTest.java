package com.xetius.calculator.Calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

@RunWith(MockitoJUnitRunner.class)
public class FileProcessingCalculatorTest {

    @InjectMocks
    private FileProcessingCalculator calculator;

    @Test
    public void calculateGetsFileContentsFrom() throws Exception {
        String fileName = new File("src/test/resources/testfile1.txt").getAbsolutePath();


    }
}