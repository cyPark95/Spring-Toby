package pcy.study.tobycleanspinrgpart1.application.required;

import pcy.study.tobycleanspinrgpart1.domain.Email;

/**
 * 이메일을 발송한다.
 */
public interface EmailSender {

    void send(Email email, String subject, String body);
}
