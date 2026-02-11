package pcy.study.tobycleanspinrg.adapter.integration;

import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Component;
import pcy.study.tobycleanspinrg.application.required.EmailSender;
import pcy.study.tobycleanspinrg.domain.Email;

@Component
@Fallback
public class DummyEmailSender implements EmailSender {

    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("Dummy EmailSender: " + email);
    }
}
