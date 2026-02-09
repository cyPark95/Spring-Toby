package pcy.study.tobycleanspinrgpart1.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void createMember() {
        // when
        var member = new Member("toby@splean.app", "Toby", "secret");

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void constructorNullCheck() {
        // when
        // then
        assertThatThrownBy(() -> new Member(null, "Toby", "secret"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void activate() {
        // given
        var member = new Member("toby@splean.app", "Toby", "secret");

        // when
        member.activate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void activateFail() {
        // given
        var member = new Member("toby@splean.app", "Toby", "secret");
        member.activate();

        // when
        // then
        assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate() {
        // given
        var member = new Member("toby@splean.app", "Toby", "secret");
        member.activate();

        // when
        member.deactivate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void deactivateFail() {
        // given
        var member = new Member("toby@splean.app", "Toby", "secret");

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
}
