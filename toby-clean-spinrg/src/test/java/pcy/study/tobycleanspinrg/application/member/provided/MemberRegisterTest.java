package pcy.study.tobycleanspinrg.application.member.provided;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.tobycleanspinrg.SplearnTestConfiguration;
import pcy.study.tobycleanspinrg.domain.member.*;
import pcy.study.tobycleanspinrg.domain.member.request.MemberInfoUpdateRequest;
import pcy.study.tobycleanspinrg.domain.member.request.MemberRegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
public record MemberRegisterTest(
        MemberRegister memberRegister,
        EntityManager entityManager
) {

    @Test
    void register() {
        // given
        MemberRegisterRequest registerRequest = MemberFixture.createMemberRegisterRequest();

        // when
        Member member = memberRegister.register(registerRequest);

        // then
        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void duplicateEmailFail() {
        // given
        MemberRegisterRequest registerRequest = MemberFixture.createMemberRegisterRequest();
        memberRegister.register(registerRequest);

        // when
        // then
        assertThatThrownBy(() -> memberRegister.register(registerRequest))
                .isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    void activate() {
        // given
        Member member = registerMember();

        // when
        Member activeMember = memberRegister.activate(member.getId());
        entityManager.flush();

        // then
        assertThat(activeMember.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(activeMember.getDetail().getActivatedAt()).isNotNull();
    }

    @Test
    void deactivate() {
        // given
        Member member = registerMember();

        memberRegister.activate(member.getId());
        entityManager.flush();
        entityManager.clear();

        // when
        Member deactiveMember = memberRegister.deactivate(member.getId());
        entityManager.flush();

        // then
        assertThat(deactiveMember.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
        assertThat(deactiveMember.getDetail().getDeactivatedAt()).isNotNull();
    }

    @Test
    void updateInfo() {
        // given
        Member member = registerMember();
        memberRegister.activate(member.getId());
        entityManager.flush();
        entityManager.clear();

        MemberInfoUpdateRequest updateRequest = new MemberInfoUpdateRequest("Peter", "tobycleanspring", "자기소개");

        // when
        Member updateMember = memberRegister.updateInfo(member.getId(), updateRequest);
        entityManager.flush();

        // then
        assertThat(updateMember.getNickname()).isEqualTo(updateRequest.nickname());
        assertThat(updateMember.getDetail().getProfile().address()).isEqualTo(updateRequest.profileAddress());
        assertThat(updateMember.getDetail().getIntroduction()).isEqualTo(updateRequest.introduction());
    }

    @Test
    void updateInfoFail() {
        // given
        Member baseMember = registerMember();
        memberRegister.activate(baseMember.getId());
        MemberInfoUpdateRequest updateRequest = new MemberInfoUpdateRequest("Peter", "tobycleanspring", "자기소개");
        memberRegister.updateInfo(baseMember.getId(), updateRequest);

        Member member = registerMember("pcy@splearn.app");
        entityManager.flush();
        entityManager.clear();

        // when
        // then
        assertThatThrownBy(() -> memberRegister.updateInfo(member.getId(), updateRequest))
                .isInstanceOf(DuplicateProfileException.class);
    }

    @Test
    void memberRegisterRequestFail() {
        // when
        // then
        checkValidation(new MemberRegisterRequest("toby@splearn.app", "Toby", "longsecret"));
        checkValidation(new MemberRegisterRequest("toby@splearn.app", "Charlie______________", "longsecret"));
        checkValidation(new MemberRegisterRequest("tobysplearn.app", "Charlie", "longsecret"));
    }

    private void checkValidation(MemberRegisterRequest invalid) {
        assertThatThrownBy(() -> memberRegister.register(invalid))
                .isInstanceOf(ConstraintViolationException.class);
    }

    private Member registerMember() {
        MemberRegisterRequest registerRequest = MemberFixture.createMemberRegisterRequest();
        Member member = memberRegister.register(registerRequest);
        entityManager.flush();
        entityManager.clear();
        return member;
    }

    private Member registerMember(String email) {
        MemberRegisterRequest registerRequest = MemberFixture.createMemberRegisterRequest(email);
        Member member = memberRegister.register(registerRequest);
        entityManager.flush();
        entityManager.clear();
        return member;
    }
}
