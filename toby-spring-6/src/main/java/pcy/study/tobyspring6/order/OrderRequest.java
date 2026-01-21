package pcy.study.tobyspring6.order;

import java.math.BigDecimal;

public record OrderRequest(String no, BigDecimal total) {
}
