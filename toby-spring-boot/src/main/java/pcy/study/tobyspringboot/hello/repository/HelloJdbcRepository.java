package pcy.study.tobyspringboot.hello.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pcy.study.tobyspringboot.hello.Hello;

@Repository
@RequiredArgsConstructor
public class HelloJdbcRepository implements HelloRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Hello findHello(String name) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM hello WHERE name = '" + name + "'",
                    (rs, rowNum) -> new Hello(
                            rs.getString("name"),
                            rs.getInt("count")
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void increaseCount(String name) {
        Hello hello = findHello(name);
        if (hello == null) {
            jdbcTemplate.update("INSERT INTO hello VALUES (?, ?)", name, 1);
            return;
        }

        jdbcTemplate.update("UPDATE hello SET count = ? WHERE name = ?", hello.getCount() + 1, name);
    }
}
