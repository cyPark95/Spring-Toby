package pcy.study.tobyspring6.learningtest.clock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockTest {

    @Test
    @DisplayName("Clock을 이용하는 LocalDateTime.now 검증")
    void clock() {
        // given
        Clock clock = Clock.systemDefaultZone();

        // when
        LocalDateTime dateTime = LocalDateTime.now(clock);
        LocalDateTime afterDateTime = LocalDateTime.now(clock);

        // then
        assertThat(afterDateTime).isAfter(dateTime);
    }

    @Test
    @DisplayName("Fixed Clock을 사용해서 원하는 현재 시간 조회 검증")
    void fixedClock() {
        // given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        // when
        LocalDateTime dateTime = LocalDateTime.now(clock);
        LocalDateTime sameDateTime = LocalDateTime.now(clock);
        LocalDateTime afterDateTime = LocalDateTime.now(clock).plusHours(1);

        // then
        assertThat(sameDateTime).isEqualTo(dateTime);
        assertThat(afterDateTime).isEqualTo(dateTime.plusHours(1));
    }
}
