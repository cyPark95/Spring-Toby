package pcy.study.tobycleanspinrgpart1.application.provided;

import jakarta.validation.Valid;
import pcy.study.tobycleanspinrgpart1.domain.Member;
import pcy.study.tobycleanspinrgpart1.domain.request.MemberRegisterRequest;

/**
 * 회원의 등록과 관련된 기능을 제공한다.
 */
public interface MemberRegister {

    Member register(@Valid MemberRegisterRequest registerRequest);

    Member activate(Long memberId);
}
