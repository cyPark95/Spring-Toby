package pcy.study.tobycleanspinrgpart1.application;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.tobycleanspinrgpart1.application.provided.MemberRegister;
import pcy.study.tobycleanspinrgpart1.application.required.EmailSender;
import pcy.study.tobycleanspinrgpart1.application.required.MemberRepository;
import pcy.study.tobycleanspinrgpart1.domain.Email;
import pcy.study.tobycleanspinrgpart1.domain.Member;
import pcy.study.tobycleanspinrgpart1.domain.MemberStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static pcy.study.tobycleanspinrgpart1.domain.MemberFixture.createMemberRegisterRequest;
import static pcy.study.tobycleanspinrgpart1.domain.MemberFixture.createPasswordEncoder;

class MemberRegisterManualTest {

    @Test
    void registerWithStub() {
        // given
        MemberRegister register = new MemberService(
                new MemberRepositoryStub(),
                new EmailSenderStub(),
                createPasswordEncoder()
        );

        // when
        Member member = register.register(createMemberRegisterRequest());

        // then
        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void registerWithMock() {
        // given
        EmailSenderMock emailSenderMock = new EmailSenderMock();

        MemberRegister register = new MemberService(
                new MemberRepositoryStub(),
                emailSenderMock,
                createPasswordEncoder()
        );

        // when
        Member member = register.register(createMemberRegisterRequest());

        // then
        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);

        List<Email> tos = emailSenderMock.getTos();
        assertThat(tos).hasSize(1);
        assertThat(tos.getFirst()).isEqualTo(member.getEmail());
    }

    @Test
    void registerWithMockito() {
        // given
        EmailSender emailSenderMock = Mockito.mock(EmailSender.class);

        MemberRegister register = new MemberService(
                new MemberRepositoryStub(),
                emailSenderMock,
                createPasswordEncoder()
        );

        // when
        Member member = register.register(createMemberRegisterRequest());

        // then
        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);

        Mockito.verify(emailSenderMock).send(eq(member.getEmail()), any(), any());
    }

    static class MemberRepositoryStub implements MemberRepository {

        @Override
        public Member save(Member member) {
            ReflectionTestUtils.setField(member, "id", 1L);
            return member;
        }

        @Override
        public Optional<Member> findByEmail(Email email) {
            return Optional.empty();
        }
    }

    static class EmailSenderStub implements EmailSender {

        @Override
        public void send(Email email, String subject, String body) {
        }
    }

    static class EmailSenderMock implements EmailSender {

        private final List<Email> tos = new ArrayList<>();

        @Override
        public void send(Email email, String subject, String body) {
            tos.add(email);
        }

        public List<Email> getTos() {
            return tos;
        }
    }
}
