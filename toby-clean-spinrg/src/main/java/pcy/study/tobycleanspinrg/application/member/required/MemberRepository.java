package pcy.study.tobycleanspinrg.application.member.required;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pcy.study.tobycleanspinrg.domain.member.Member;
import pcy.study.tobycleanspinrg.domain.member.Profile;
import pcy.study.tobycleanspinrg.domain.shared.Email;

import java.util.Optional;

/**
 * 회원 정보를 저장하거나 조회한다.
 */
public interface MemberRepository extends Repository<Member, Long> {

    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long id);

    @Query("SELECT m FROM Member m WHERE m.detail.profile = :profile")
    Optional<Member> findByProfile(Profile profile);
}
