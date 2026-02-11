package pcy.study.tobycleanspinrgpart1.application.provided;

import pcy.study.tobycleanspinrgpart1.domain.Member;

/**
 * 회원을 조회한다.
 */
public interface MemberFinder {

    Member find(Long memberId);
}
