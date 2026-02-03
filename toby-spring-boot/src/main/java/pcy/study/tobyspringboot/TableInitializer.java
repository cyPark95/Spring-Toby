package pcy.study.tobyspringboot;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TableInitializer {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    void init() {
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS hello (
                name varchar(50) PRIMARY KEY,
                count int
            )
        """);
    }
}
