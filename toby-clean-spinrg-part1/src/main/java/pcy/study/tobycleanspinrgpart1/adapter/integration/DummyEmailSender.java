package pcy.study.tobycleanspinrgpart1.adapter.integration;

import org.springframework.stereotype.Component;
import pcy.study.tobycleanspinrgpart1.application.required.EmailSender;
import pcy.study.tobycleanspinrgpart1.domain.Email;

@Component
public class DummyEmailSender implements EmailSender {

    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("Dummy EmailSender: " + email);
    }
}
