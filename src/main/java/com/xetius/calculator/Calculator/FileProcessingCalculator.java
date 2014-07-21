package com.xetius.calculator.Calculator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileProcessingCalculator implements Calculator {
    public static final String ERROR = "Error";
    private Map<String, String> parameters;
    private SourceProcessor source;
    private SourceParser parser;

    @Override
    public void setParameters(String[] args) {
    }

    @Override
    public long calculate() throws IOException, InvalidStatementsException {
        List<String> statements = source.process(parameters);
        return parser.parse(statements);
    }


}
