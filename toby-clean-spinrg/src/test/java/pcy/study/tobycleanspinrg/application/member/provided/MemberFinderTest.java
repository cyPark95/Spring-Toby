package pcy.study.tobycleanspinrg.application.member.provided;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.tobycleanspinrg.SplearnTestConfiguration;
import pcy.study.tobycleanspinrg.domain.member.Member;
import pcy.study.tobycleanspinrg.domain.member.MemberFixture;
import pcy.study.tobycleanspinrg.domain.member.request.MemberRegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
record MemberFinderTest(
        MemberFinder memberFinder,
        MemberRegister memberRegister,
        EntityManager entityManager
) {

    @Test
    void find() {
        // given
        MemberRegisterRequest registerRequest = MemberFixture.createMemberRegisterRequest();
        Member member = memberRegister.register(registerRequest);
        entityManager.flush();
        entityManager.clear();

        // when
        Member found = memberFinder.find(member.getId());

        // then
        assertThat(member.getId()).isEqualTo(found.getId());
    }

    @Test
    void findFail() {
        // when
        // then
        assertThatThrownBy(() -> memberFinder.find(-1L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
