package pcy.study.tobycleanspinrg.application.required;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import pcy.study.tobycleanspinrg.domain.Member;
import pcy.study.tobycleanspinrg.domain.MemberFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.tobycleanspinrg.domain.MemberFixture.createPasswordEncoder;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void createMember() {
        // given
        Member member = Member.register(MemberFixture.createMemberRegisterRequest(), createPasswordEncoder());

        // when
        memberRepository.save(member);

        // then
        assertThat(member.getId()).isNotNull();
        entityManager.flush();
    }

    @Test
    void duplicateEmailFail() {
        // given
        Member member = Member.register(MemberFixture.createMemberRegisterRequest(), createPasswordEncoder());
        memberRepository.save(member);

        Member newMember = Member.register(MemberFixture.createMemberRegisterRequest(), createPasswordEncoder());

        // when
        // then
        assertThatThrownBy(() -> memberRepository.save(newMember))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
