package com.agileengine.html_parser;

import com.agileengine.html_parser.analizer.ResultsAnalyzer;
import com.agileengine.html_parser.domain.Result;
import com.agileengine.html_parser.parser.Parser;
import com.agileengine.html_parser.writer.ConsoleWriter;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Application {

  public static void main(String[] args) throws IOException {

    final String originFilePath = args[0];
    final String sampleFilePath = args[1];
    final String elementId = args[2];

    Parser parser = new Parser(originFilePath, sampleFilePath);
    ResultsAnalyzer analyzer = new ResultsAnalyzer();
    ConsoleWriter consoleWriter = new ConsoleWriter();

    Element elementOriginal = parser.extractOriginalElement(elementId);
    Elements elementsByTag = parser.extractElementsByTag(elementOriginal.tagName());

    List<Result> matchingResults = analyzer.getMatchingResults(elementOriginal, elementsByTag);

    consoleWriter.printOriginalAttributes(elementOriginal);
    consoleWriter.printMatchedAttributes(matchingResults);
  }

}
