package pcy.study.tobycleanspinrgpart1.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pcy.study.tobycleanspinrgpart1.application.provided.MemberFinder;
import pcy.study.tobycleanspinrgpart1.application.required.MemberRepository;
import pcy.study.tobycleanspinrgpart1.domain.Member;

@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class MemberQueryService implements MemberFinder {

    private final MemberRepository memberRepository;

    @Override
    public Member find(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + memberId));
    }
}
