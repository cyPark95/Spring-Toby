package pcy.study.tobycleanspinrgpart1.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record MemberRegisterRequest(
        @Email
        String email,
        @Size(min = 5, max = 20)
        String nickname,
        @Size(min = 8, max = 20)
        String password
) {
}
