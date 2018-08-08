package com.agileengine.html_parser.analizer;

import com.agileengine.html_parser.domain.Result;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author Leonid Yavorskyi
 */
public class ResultsAnalyzer {

  public List<Result> getMatchingResults(Element original, Elements similar) {

    List<Result> results = similar.stream()
        .map(Result::new)
        .collect(toList());

    addMatchingAttributes(original, results);
    addMatchingTextFields(original, results);

    // Returns list of results, cause can be case when several matching elements have the same value
    List<Result> matchingResults = results.stream()
        .collect(groupingBy(Function.identity(), TreeMap::new, toList()))
        .lastEntry()
        .getValue();

    matchingResults.forEach(result -> result.setElementPath(xmlPath(result.getElement())));

    return matchingResults;
  }

  private String xmlPath(Element element) {
    List<String> pathElements = element.parents().stream()
        .map(this::formatElementPosition)
        .collect(Collectors.toList());
    Collections.reverse(pathElements);
    pathElements.add(formatElementPosition(element));
    return String.join(" > ", pathElements);
  }

  private String formatElementPosition(Element element) {
    return String.format("%s[%s]", element.tagName(), element.elementSiblingIndex());
  }

  private void addMatchingAttributes(Element original, List<Result> results) {
    Attributes originalAttributes = original.attributes();

    Map<String, String> originalAttributesMap = originalAttributes.asList()
        .stream()
        .collect(Collectors.toMap(Attribute::getKey, Attribute::getValue));

    results.forEach(result -> result.getElement().attributes().asList().stream()
        .filter(a -> originalAttributesMap.containsKey(a.getKey()) && originalAttributesMap.get(a.getKey())
            .equals(a.getValue()))
        .forEach(a -> result.addMatchingElement(a.getKey(), a.getValue()))
    );
  }

  private void addMatchingTextFields(Element original, List<Result> results) {
    results.stream()
        .filter(result -> isTextMatched(original).test(result.getElement()))
        .forEach(result -> result.addMatchingElement("text", result.getElement().text()));
  }

  private static Predicate<Element> isTextMatched(Element p2) {
    return p1 -> p1.hasText() && p2.hasText() && p1.text().equals(p2.text());
  }

}
