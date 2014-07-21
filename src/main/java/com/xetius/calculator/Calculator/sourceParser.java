package com.xetius.calculator.Calculator;

import java.util.List;

public interface SourceParser {
    public long parse(List<String> statements) throws InvalidStatementsException;
}
