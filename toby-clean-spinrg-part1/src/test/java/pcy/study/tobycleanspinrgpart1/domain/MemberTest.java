package pcy.study.tobycleanspinrgpart1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pcy.study.tobycleanspinrgpart1.domain.request.MemberRegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.tobycleanspinrgpart1.domain.MemberFixture.createMemberRegisterRequest;
import static pcy.study.tobycleanspinrgpart1.domain.MemberFixture.createPasswordEncoder;

class MemberTest {

    private PasswordEncoder passwordEncoder;

    private Member member;

    @BeforeEach
    void setUp() {
        passwordEncoder = createPasswordEncoder();
        member = Member.register(createMemberRegisterRequest(), passwordEncoder);
    }

    @Test
    void registerMember() {
        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void activate() {
        // when
        member.activate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void activateFail() {
        // given
        member.activate();

        // when
        // then
        assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate() {
        // given
        member.activate();

        // when
        member.deactivate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void deactivateFail() {
        // when
        // then
        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);

        // given
        member.activate();
        member.deactivate();

        // when
        // then
        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void verifyPassword() {
        // when
        // then
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();

        // when
        // then
        assertThat(member.verifyPassword("hello", passwordEncoder)).isFalse();
    }

    @Test
    void changeNickname() {
        // when
        member.changeNickname("Charlie");

        // then
        assertThat(member.getNickname()).isEqualTo("Charlie");
    }

    @Test
    void changePassword() {
        // when
        member.changePassword("verySecret", passwordEncoder);

        // then
        assertThat(member.verifyPassword("verySecret", passwordEncoder)).isTrue();
    }

    @Test
    void isActive() {
        // when
        // then
        assertThat(member.isActive()).isFalse();

        // given
        member.activate();

        // when
        // then
        assertThat(member.isActive()).isTrue();
    }

    @Test
    void invalidEmail() {
        // given
        MemberRegisterRequest createRequest = createMemberRegisterRequest("invalid email");

        // when
        // then
        assertThatThrownBy(() -> Member.register(createRequest, passwordEncoder))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
