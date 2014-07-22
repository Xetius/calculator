package com.xetius.calculator.processor;

import com.google.common.io.Files;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class FileSourceProcessor implements SourceProcessor {

    private static final java.nio.charset.Charset DEFAULT_ENCODING = Charset.defaultCharset();

    public List<String> process(Map<String, String> parameters) throws IOException {
        String fileName = parameters.get("filename");
        List<String> contents = getContentsFromFileSystem(fileName);
        if (contents == null) {
            contents = getContentsFromClassPath(fileName);
        }

        if (contents == null) {
            throw new IOException("Unable to locate file " + fileName);
        }
        return contents;
    }

    private List<String> getContentsFromClassPath(String fileName) {
        try {
            URL url = Resources.getResource(fileName);
            return Resources.readLines(url, DEFAULT_ENCODING);
        } catch (IOException | IllegalArgumentException e) {
            return null;
        }
    }

    private List<String> getContentsFromFileSystem(String fileName) {
        try {
            File file = new File(fileName);
            return Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }
}
