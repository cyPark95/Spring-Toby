package pcy.study.tobycleanspinrgpart1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    private PasswordEncoder passwordEncoder;

    private Member member;

    @BeforeEach
    void setUp() {
        passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matched(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };

        member = Member.create("toby@splean.app", "Toby", "secret", passwordEncoder);
    }

    @Test
    void createMember() {
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
}
