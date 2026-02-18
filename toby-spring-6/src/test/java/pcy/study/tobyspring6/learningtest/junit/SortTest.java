package pcy.study.tobyspring6.learningtest.junit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SortTest {

    private Sort sort;

    @BeforeEach
    void setUp() {
        sort = new Sort();
    }

    @Test
    void sort() {
        // 준비 (Given)
        List<String> words = Arrays.asList("Java", "Hi");

        // 실행 (When)
        List<String> result = sort.sortByLength(words);

        // 검증 (Then)
        Assertions.assertThat(result).isEqualTo(List.of("Hi", "Java"));
    }

    @Test
    void sort3Items() {
        // given
        Sort sort = new Sort();
        List<String> words = Arrays.asList("Spring", "Hi", "Java");

        // when
        List<String> result = sort.sortByLength(words);

        // then
        Assertions.assertThat(result).isEqualTo(List.of("Hi", "Java", "Spring"));
    }

    @Test
    void sortAlreadySorted() {
        // given
        Sort sort = new Sort();
        List<String> words = Arrays.asList("Hi", "Java", "Spring");

        // when
        List<String> result = sort.sortByLength(words);

        // then
        Assertions.assertThat(result).isEqualTo(List.of("Hi", "Java", "Spring"));
    }
}
