package com.xetius.calculator.calculator;

import com.google.common.collect.ImmutableMap;
import com.xetius.calculator.processor.FileSourceProcessor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class FileSourceProcessorTest {

    @InjectMocks
    private FileSourceProcessor processor;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void processReturnsContentsOfFileAsListOfStringsWhenFileNameIsOnClassPath() throws Exception {
        Map<String, String> parameters = ImmutableMap.of("filename", "testfile1.txt");

        List<String> contents = processor.process(parameters);

        assertThat(contents, hasSize(3));
    }

    @Test
    public void processReturnsContentsOfFileAsListOfStringsWhenFileNameIsRelativeFilePath() throws Exception {
        Map<String, String> parameters = ImmutableMap.of("filename", "src/test/resources/testfile1.txt");

        List<String> contents = processor.process(parameters);

        assertThat(contents, hasSize(3));
    }

    @Test
    public void processThrowsExceptionForUnknownFileName() throws Exception {
        exception.expect(IOException.class);
        exception.expectMessage("Unable to locate file unknownfile.txt");

        Map<String, String> parameters = ImmutableMap.of("filename", "unknownfile.txt");

        processor.process(parameters);
    }
}