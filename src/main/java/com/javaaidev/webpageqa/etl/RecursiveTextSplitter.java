package com.javaaidev.webpageqa.etl;

import dev.langchain4j.data.document.DefaultDocument;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import java.util.List;
import org.springframework.ai.transformer.splitter.TextSplitter;

public class RecursiveTextSplitter extends TextSplitter {

  @Override
  protected List<String> splitText(String text) {
    var splitter = DocumentSplitters.recursive(500, 100);
    return splitter.split(new DefaultDocument(text))
        .stream()
        .map(TextSegment::text)
        .toList();
  }
}
