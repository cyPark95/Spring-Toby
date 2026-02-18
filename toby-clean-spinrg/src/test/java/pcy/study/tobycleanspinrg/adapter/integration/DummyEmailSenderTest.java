package pcy.study.tobycleanspinrg.adapter.integration;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;
import pcy.study.tobycleanspinrg.domain.shared.Email;

import static org.assertj.core.api.Assertions.assertThat;

class DummyEmailSenderTest {

    @Test
    @StdIo
    void dummyEmailSender(StdOut out) {
        // given
        DummyEmailSender dummyEmailSender = new DummyEmailSender();

        // when
        dummyEmailSender.send(new Email("toby@splearn.app"), "subject", "body");

        // then
        assertThat(out.capturedLines()[0]).isEqualTo("Dummy EmailSender: Email[address=toby@splearn.app]");
    }
}
