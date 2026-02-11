package pcy.study.tobycleanspinrgpart1.application.provided;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.tobycleanspinrgpart1.SplearnTestConfiguration;
import pcy.study.tobycleanspinrgpart1.domain.Member;
import pcy.study.tobycleanspinrgpart1.domain.MemberFixture;
import pcy.study.tobycleanspinrgpart1.domain.request.MemberRegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
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
