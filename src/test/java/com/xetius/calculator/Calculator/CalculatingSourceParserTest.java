package com.xetius.calculator.Calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(MockitoJUnitRunner.class)
public class CalculatingSourceParserTest {

    @InjectMocks
    private CalculatingSourceParser parser;

    @Rule public ExpectedException exception = ExpectedException.none();

    @Test
    public void emptyStatementsThrowsAnException() throws Exception {
        exception.expect(InvalidStatementsException.class);
        exception.expectMessage(InvalidStatementsException.NO_STATEMENTS_PROVIDED);

        parser.parse(new ArrayList<>());
    }

    @Test
    public void nullStatementsThrowsAnException() throws Exception {
        exception.expect(InvalidStatementsException.class);
        exception.expectMessage(InvalidStatementsException.NO_STATEMENTS_PROVIDED);

        parser.parse(null);
    }

    @Test
    public void parseThrowsExceptionWhenLastStatementIsNotApply() throws Exception {
        exception.expect(InvalidStatementsException.class);
        exception.expectMessage(InvalidStatementsException.INVALID_STATEMENTS_PROVIDED);

        List<String> statements = Arrays.asList("add 5");

        parser.parse(statements);
    }

    @Test
    public void parseReturnsAValueWhenAnApplyIsSuppliedAsTheOnlyValue() throws Exception {
        List<String> statements = Arrays.asList("apply 5");

        long result = parser.parse(statements);

        assertThat(result, equalTo(5L));
    }

    @Test
    public void parseAddsToValue() throws Exception {
        List<String> statements = Arrays.asList("add 3", "apply 5");

        long result = parser.parse(statements);

        assertThat(result, equalTo(8L));
    }

    @Test
    public void parseSubtractsFromValue() throws Exception {
        List<String> statements = Arrays.asList("subtract 3", "apply 5");

        long result = parser.parse(statements);

        assertThat(result, equalTo(2L));
    }

    @Test
    public void parseMultipliesWithValue() throws Exception {
        List<String> statements = Arrays.asList("multiply 3", "apply 5");

        long result = parser.parse(statements);

        assertThat(result, equalTo(15L));
    }

    @Test
    public void parseDividesByValue() throws Exception {
        List<String> statements = Arrays.asList("divide 3", "apply 12");

        long result = parser.parse(statements);

        assertThat(result, equalTo(4L));
    }

    @Test
    public void parseUnknownOperatorThrowsException() throws Exception {
        exception.expect(InvalidStatementsException.class);
        exception.expectMessage(InvalidStatementsException.INVALID_STATEMENTS_PROVIDED);

        List<String> statements = Arrays.asList("unknown 3", "apply 5");

        parser.parse(statements);
    }
}