package pcy.study.tobycleanspinrgpart1.domain.request;

public record MemberCreateRequest(
        String email,
        String nickname,
        String password
) {
}
