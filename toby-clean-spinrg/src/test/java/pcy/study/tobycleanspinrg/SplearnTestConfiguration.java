package pcy.study.tobycleanspinrg;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import pcy.study.tobycleanspinrg.application.required.EmailSender;
import pcy.study.tobycleanspinrg.domain.PasswordEncoder;

import static pcy.study.tobycleanspinrg.domain.MemberFixture.createPasswordEncoder;

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
