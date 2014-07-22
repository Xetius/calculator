package com.xetius.calculator.calculator;

import com.xetius.calculator.exception.InvalidArgumentException;
import com.xetius.calculator.exception.InvalidStatementsException;
import com.xetius.calculator.parser.SourceParser;
import com.xetius.calculator.processor.SourceProcessor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileProcessingCalculator implements Calculator {
    private Map<String, String> parameters;

    private SourceProcessor source;
    private SourceParser parser;

    public FileProcessingCalculator(SourceProcessor source, SourceParser parser) {
        this.source = source;
        this.parser = parser;
    }

    @Override
    public void setParameters(String[] args) throws InvalidArgumentException {
        if (args.length > 0) {
            parameters = new HashMap<>();
            parameters.put("filename", args[0]);
            return;
        }
        throw new InvalidArgumentException("No filename supplied");
    }

    @Override
    public long calculate() throws IOException, InvalidStatementsException {
        List<String> statements = source.process(parameters);
        return parser.parse(statements);
    }


}
