package pcy.study.tobycleanspinrg.application.provided;

import pcy.study.tobycleanspinrg.domain.Member;

/**
 * 회원을 조회한다.
 */
public interface MemberFinder {

    Member find(Long memberId);
}
