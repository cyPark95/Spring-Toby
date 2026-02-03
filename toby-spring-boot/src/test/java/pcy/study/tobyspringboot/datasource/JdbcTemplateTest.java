package pcy.study.tobyspringboot.datasource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.tobyspringboot.HelloBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@HelloBootTest
@Transactional
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS hello(
                    name varchar(50) primary key,
                    count int
                )
                """);
    }

    @Test
    void insertAndQuery() {
        // given
        jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "Toby", 3);
        jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "pcy", 1);

        // when
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM hello", Long.class);

        // then
        assertThat(count).isEqualTo(2);
    }
}
