package com.xetius.calculator.calculator;

import com.xetius.calculator.exception.InvalidArgumentException;
import com.xetius.calculator.exception.InvalidStatementsException;
import com.xetius.calculator.parser.SourceParser;
import com.xetius.calculator.processor.SourceProcessor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FileProcessingCalculatorTest {

    @InjectMocks
    private FileProcessingCalculator calculator;

    @Mock
    private SourceParser parser;

    @Mock
    private SourceProcessor processor;

    @Captor
    ArgumentCaptor<Map<String, String>> param;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws InvalidStatementsException {
        calculator = new FileProcessingCalculator(processor, parser);
        given(parser.parse(anyListOf(String.class))).willReturn(15L);
    }

    @Test
    public void calculateCalculatePassesArgAsFilenameParameter() throws Exception {
        String[] args = {"testFilename"};

        calculator.setParameters(args);
        calculator.calculate();

        verify(processor).process(param.capture());
        Map<String, String> calculatorParameters = param.getValue();
        assertThat(calculatorParameters.size(), equalTo(1));
        assertThat(calculatorParameters.keySet(), contains("filename"));
        assertThat(calculatorParameters.values(), contains("testFilename"));
    }

    @Test
    public void passingEmptyArgumentsThrowsException() throws Exception {
        String[] args = new String[]{};

        exception.expect(InvalidArgumentException.class);
        exception.expectMessage("No filename supplied");
        calculator.setParameters(args);
        calculator.calculate();



    }
}