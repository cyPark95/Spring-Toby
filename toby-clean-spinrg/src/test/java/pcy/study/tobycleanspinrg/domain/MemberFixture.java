package pcy.study.tobycleanspinrg.domain;

import org.springframework.lang.NonNull;
import pcy.study.tobycleanspinrg.domain.request.MemberRegisterRequest;

public class MemberFixture {

    @NonNull
    public static MemberRegisterRequest createMemberRegisterRequest() {
        return createMemberRegisterRequest("toby@splean.app");
    }

    @NonNull
    public static MemberRegisterRequest createMemberRegisterRequest(String email) {
        return new MemberRegisterRequest(email, "Charlie", "verysecret");
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
