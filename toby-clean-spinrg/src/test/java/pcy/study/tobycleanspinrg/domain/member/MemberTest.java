package pcy.study.tobycleanspinrg.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pcy.study.tobycleanspinrg.domain.member.request.MemberInfoUpdateRequest;
import pcy.study.tobycleanspinrg.domain.member.request.MemberRegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.tobycleanspinrg.domain.member.MemberFixture.createMemberRegisterRequest;
import static pcy.study.tobycleanspinrg.domain.member.MemberFixture.createPasswordEncoder;

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
        assertThat(member.getDetail().getRegisteredAt()).isNotNull();
    }

    @Test
    void activate() {
        // when
        member.activate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(member.getDetail().getActivatedAt()).isNotNull();
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
        assertThat(member.getDetail().getDeactivatedAt()).isNotNull();
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
        assertThat(member.verifyPassword("verysecret", passwordEncoder)).isTrue();

        // when
        // then
        assertThat(member.verifyPassword("hello", passwordEncoder)).isFalse();
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

    @Test
    void updateInfo() {
        // given
        member.activate();
        MemberInfoUpdateRequest updateRequest = new MemberInfoUpdateRequest("Leo", "tobycleanspring", "자기소개");

        // when
        member.updateInfo(updateRequest);

        // then
        assertThat(member.getNickname()).isEqualTo(updateRequest.nickname());
        assertThat(member.getDetail().getProfile().address()).isEqualTo(updateRequest.profileAddress());
        assertThat(member.getDetail().getIntroduction()).isEqualTo(updateRequest.introduction());
    }

    @Test
    void updateInfoFail() {
        // given
        MemberInfoUpdateRequest updateRequest = new MemberInfoUpdateRequest("Leo", "tobycleanspring", "자기소개");

        // when
        // then
        assertThatThrownBy(() -> member.updateInfo(updateRequest))
                .isInstanceOf(IllegalStateException.class);
    }
}
