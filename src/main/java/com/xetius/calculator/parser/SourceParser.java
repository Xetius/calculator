package com.xetius.calculator.parser;

import com.xetius.calculator.exception.InvalidStatementsException;

import java.util.List;

public interface SourceParser {
    public long parse(List<String> statements) throws InvalidStatementsException;
}
