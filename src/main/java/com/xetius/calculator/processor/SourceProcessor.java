package com.xetius.calculator.processor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SourceProcessor {
    public List<String> process(Map<String, String> parameters) throws IOException;
}
