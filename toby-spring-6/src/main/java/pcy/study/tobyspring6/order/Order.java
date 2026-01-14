package pcy.study.tobyspring6.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String no;

    private BigDecimal total;


    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }
}
