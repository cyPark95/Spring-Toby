package pcy.study.tobycleanspinrg.domain.member.request;

public record MemberInfoUpdateRequest(
        String nickname,
        String profileAddress,
        String introduction
) {
}
