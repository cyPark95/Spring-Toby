package pcy.study.tobycleanspinrg;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

class SplearnApplicationTest {

    @Test
    void run() {
        // given
        try(MockedStatic<SpringApplication> mocked = Mockito.mockStatic(SpringApplication.class)) {

            // when
            TobyCleanSpinrgApplication.main(new String[0]);

            // then
            mocked.verify(() -> SpringApplication.run(TobyCleanSpinrgApplication.class, new String[0]));
        }
    }
}
