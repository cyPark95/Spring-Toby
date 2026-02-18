package pcy.study.tobycleanspinrg.adapter.webapi.dto;

import pcy.study.tobycleanspinrg.domain.member.Member;

public record MemberRegisterResponse(
        Long memberId,
        String email
) {

    public static MemberRegisterResponse of(Member member) {
        return new MemberRegisterResponse(
                member.getId(),
                member.getEmail().address()
        );
    }
}
