package com.xetius.calculator.parser;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.xetius.calculator.exception.InvalidStatementsException;

import java.util.List;

public class CalculatingSourceParser implements SourceParser {

    @Override
    public long parse(List<String> statements) throws InvalidStatementsException {
        if (statements == null || statements.isEmpty()) {
            throw new InvalidStatementsException(InvalidStatementsException.NO_STATEMENTS_PROVIDED);
        }

        long value = getApplyValue(statements);
        return calculate(value, statements);
    }

    private long calculate(long value, List<String> statements) throws InvalidStatementsException {
        for (String statement : statements) {
            value = processStatement(value, statement);
        }
        return value;
    }

    private long processStatement(long value, String statement) throws InvalidStatementsException {
        List<String> items = Lists.newArrayList(Splitter.on(" ").split(statement));
        String operator = items.get(0);
        long value2 = Long.valueOf(items.get(1));
        if (items.get(0).equalsIgnoreCase("apply")) {
            return value;
        } else if (operator.equalsIgnoreCase("add")) {
            return value + value2;
        } else if (operator.equalsIgnoreCase("subtract")) {
            return value - value2;
        } else if (operator.equalsIgnoreCase("multiply")) {
            return value * value2;
        } else if (operator.equalsIgnoreCase("divide")) {
            return value / value2;
        }
        throw new InvalidStatementsException(InvalidStatementsException.INVALID_STATEMENTS_PROVIDED);
    }

    private long getApplyValue(List<String> statements) throws InvalidStatementsException {
        String lastStatement = statements.get(lastStatementIndex(statements));
        return parseApplyValue(lastStatement);
    }

    private long parseApplyValue(String lastStatement) throws InvalidStatementsException {
        List<String> items = Lists.newArrayList(Splitter.on(" ").split(lastStatement));
        String operator = items.get(0);
        long value = Long.valueOf(items.get(1));
        if (operator.equalsIgnoreCase("apply")) {
            return value;
        }
        throw new InvalidStatementsException(InvalidStatementsException.INVALID_STATEMENTS_PROVIDED);
    }

    private int lastStatementIndex(List<String> statements) {
        return statements.size()-1;
    }
}
