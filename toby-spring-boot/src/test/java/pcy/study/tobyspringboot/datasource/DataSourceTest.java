package pcy.study.tobyspringboot.datasource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void connect() throws SQLException {
        // when
        Connection connection = dataSource.getConnection();

        // then
        assertThat(connection).isNotNull();
    }
}
