package pcy.study.tobycleanspinrg.application.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pcy.study.tobycleanspinrg.application.member.provided.MemberFinder;
import pcy.study.tobycleanspinrg.application.member.required.MemberRepository;
import pcy.study.tobycleanspinrg.domain.member.Member;

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
