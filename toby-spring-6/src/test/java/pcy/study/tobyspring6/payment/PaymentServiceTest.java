package pcy.study.tobyspring6.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pcy.study.tobyspring6.exchangerate.WebApiExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메서드의 3가지 요구사항 충족 여부 검증")
    void prepare() throws IOException {
        // given
        PaymentService paymentService = new PaymentService(new WebApiExchangeRateProvider());

        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 환율 정보 조회
        assertThat(payment.getExchangeRate()).isNotNull();

        // 원화 환산 금액 계산
        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExchangeRate().multiply(payment.getForeignCurrencyAmount()));

        // 원화 환상 금액의 유효 시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}
