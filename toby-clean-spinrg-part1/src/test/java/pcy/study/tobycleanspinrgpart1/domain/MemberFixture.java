package pcy.study.tobycleanspinrgpart1.domain;

import org.springframework.lang.NonNull;
import pcy.study.tobycleanspinrgpart1.domain.request.MemberRegisterRequest;

public class MemberFixture {

    @NonNull
    public static MemberRegisterRequest createMemberRegisterRequest() {
        return createMemberRegisterRequest("toby@splean.app");
    }

    @NonNull
    public static MemberRegisterRequest createMemberRegisterRequest(String email) {
        return new MemberRegisterRequest(email, "Toby", "secret");
    }

    @NonNull
    public static PasswordEncoder createPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matched(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
    }
}
