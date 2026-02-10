package pcy.study.tobycleanspinrgpart1.domain.request;

public record MemberRegisterRequest(
        String email,
        String nickname,
        String password
) {
}
