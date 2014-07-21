package com.xetius.calculator.Calculator;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class FileSourceProcessorTest {

    @InjectMocks
    private FileSourceProcessor processor;

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


}