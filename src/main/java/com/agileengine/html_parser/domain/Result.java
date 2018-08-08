package com.agileengine.html_parser.domain;

import org.jsoup.nodes.Element;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Leonid Yavorskyi
 */
public class Result implements Comparable<Result>{
  private final Element element;
  private final Map<String, String> matchingElements;
  private int value;
  private String elementPath;

  public Result(Element element) {
    this.element = element;
    this.matchingElements = new LinkedHashMap<>();
  }

  public void addMatchingElement(String key, String value) {
    this.value++;
    this.matchingElements.put(key, value);
  }

  public Element getElement() {
    return element;
  }

  public int getValue() {
    return value;
  }

  public String getElementPath() {
    return elementPath;
  }

  public void setElementPath(String elementPath) {
    this.elementPath = elementPath;
  }

  public Map<String, String> getMatchingElements() {
    return matchingElements;
  }

  @Override
  public int compareTo(Result o) {
    return  Integer.compare(this.value, o.getValue());
  }
}

