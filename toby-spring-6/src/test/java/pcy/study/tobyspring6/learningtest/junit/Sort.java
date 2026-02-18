package pcy.study.tobyspring6.learningtest.junit;

import java.util.Comparator;
import java.util.List;

public class Sort {

    public List<String> sortByLength(List<String> words) {
        words.sort(Comparator.comparingInt(String::length));
        return words;
    }
}
