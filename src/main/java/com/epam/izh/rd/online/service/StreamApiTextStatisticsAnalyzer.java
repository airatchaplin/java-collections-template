package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        List<String> list = new ArrayList<>(getWords(text));
        Stream<String> stream = list.stream();
        int count = stream.mapToInt(String::length).sum();
        return count;
    }

    @Override
    public int countNumberOfWords(String text) {
        List<String> list = new ArrayList<>(getWords(text));
        Stream<String> stream = list.stream();
        return (int) stream.count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        Set<String> set = new HashSet<>(getWords(text));
        Stream<String> stream = set.stream();
        return (int) stream.count();
    }

    @Override
    public List<String> getWords(String text) {
        String regex = "\\W";
        List<String> list = Arrays.stream(text.split(regex)).collect(Collectors.toList());
        list.removeIf(String::isEmpty);
        return list;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        ArrayList<String> list = new ArrayList<>(getWords(text));
        return list.stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        ArrayList<String> list = new ArrayList<>(getWords(text));
        return list.stream().collect(Collectors.toMap(Function.identity(), x -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> list = new ArrayList<>(getWords(text));
        if (direction.equals(Direction.ASC)) {
            list = list.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
        } else if (direction.equals(Direction.DESC)) {
            list = list.stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
        }
        return list;

    }
}