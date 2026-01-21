package pcy.study.tobyspring6.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Order {

    @Setter
    private Long id;

    private String no;

    private BigDecimal total;

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }
}
