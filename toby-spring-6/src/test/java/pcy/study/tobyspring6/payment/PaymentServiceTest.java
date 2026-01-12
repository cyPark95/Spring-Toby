package pcy.study.tobyspring6.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메서드의 3가지 요구사항 충족 여부 검증")
    void convertedAmount() throws IOException {
        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
        testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000));
        testAmount(BigDecimal.valueOf(3_000), BigDecimal.valueOf(30_000));
    }

    @Test
    void validUntil() throws IOException {
        // given
        PaymentService paymentService = new PaymentService(new ExchangeProviderStub(BigDecimal.valueOf(1_000)), clock);

        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        LocalDateTime expectedValidUntil = LocalDateTime.now(clock).plusMinutes(30);
        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private void testAmount(BigDecimal exchangeRate, BigDecimal convertedAmount) throws IOException {
        // given
        PaymentService paymentService = new PaymentService(new ExchangeProviderStub(exchangeRate), clock);

        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 환율 정보 조회
        assertThat(payment.getExchangeRate()).isEqualByComparingTo(exchangeRate);
        // 원화 환산 금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}
