package com.agileengine.html_parser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Leonid Yavorskyi
 */
public class Parser {

  private final String originPath;
  private final String samplePath;

  public Parser(String originPath, String samplePath) {
    this.originPath = originPath;
    this.samplePath = samplePath;
  }

  public Element extractOriginalElement(String originalId) throws IOException {
    File file = new File(originPath);
    Document document = Jsoup.parse(file, "UTF-8");
    return Optional.ofNullable(document.body()
        .getElementById(originalId))
        .orElseThrow(() -> new RuntimeException(String.format("Can't find element with id %s.", originalId)));
  }

  public Elements extractElementsByTag(String tag) throws IOException {
    File file = new File(samplePath);
    Document document = Jsoup.parse(file, "UTF-8");
    return Optional.ofNullable(document.getElementsByTag(tag))
        .orElseThrow(() -> new RuntimeException(String.format("Can't find element by tag [%s].", tag)));
  }
}
