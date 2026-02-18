package pcy.study.tobycleanspinrg.application.member.required;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import pcy.study.tobycleanspinrg.domain.member.Member;
import pcy.study.tobycleanspinrg.domain.member.MemberFixture;
import pcy.study.tobycleanspinrg.domain.member.MemberStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void createMember() {
        // given
        Member member = MemberFixture.createMember();

        // when
        memberRepository.save(member);

        // then
        assertThat(member.getId()).isNotNull();

        entityManager.flush();
        entityManager.clear();

        Member found = memberRepository.findById(member.getId()).orElseThrow();
        assertThat(found.getStatus()).isEqualTo(MemberStatus.PENDING);
        assertThat(found.getDetail().getRegisteredAt()).isNotNull();
    }

    @Test
    void duplicateEmailFail() {
        // given
        Member member = MemberFixture.createMember();
        memberRepository.save(member);

        Member newMember = MemberFixture.createMember();

        // when
        // then
        assertThatThrownBy(() -> memberRepository.save(newMember))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
