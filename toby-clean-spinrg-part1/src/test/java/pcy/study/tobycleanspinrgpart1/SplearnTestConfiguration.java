package pcy.study.tobycleanspinrgpart1;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import pcy.study.tobycleanspinrgpart1.application.required.EmailSender;
import pcy.study.tobycleanspinrgpart1.domain.PasswordEncoder;

import static pcy.study.tobycleanspinrgpart1.domain.MemberFixture.createPasswordEncoder;

@TestConfiguration
public class SplearnTestConfiguration {

    @Bean
    public EmailSender emailSender() {
        return (email, subject, body) -> System.out.println("Sending Email: " + email);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return createPasswordEncoder();
    }
}
