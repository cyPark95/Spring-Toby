package pcy.study.tobycleanspinrgpart1.application;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.tobycleanspinrgpart1.SplearnTestConfiguration;
import pcy.study.tobycleanspinrgpart1.application.provided.MemberRegister;
import pcy.study.tobycleanspinrgpart1.domain.DuplicateEmailException;
import pcy.study.tobycleanspinrgpart1.domain.Member;
import pcy.study.tobycleanspinrgpart1.domain.MemberFixture;
import pcy.study.tobycleanspinrgpart1.domain.MemberStatus;
import pcy.study.tobycleanspinrgpart1.domain.request.MemberRegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public record MemberRegisterTest(MemberRegister memberRegister) {

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
}
