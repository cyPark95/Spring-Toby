package pcy.study.tobycleanspinrg.domain.member;

import org.springframework.lang.NonNull;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.tobycleanspinrg.domain.member.request.MemberRegisterRequest;

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

    public static Member createMember() {
        return Member.register(createMemberRegisterRequest(), createPasswordEncoder());
    }

    public static Member createMember(Long id) {
        Member member = Member.register(createMemberRegisterRequest(), createPasswordEncoder());
        ReflectionTestUtils.setField(member, "id", id);
        return member;
    }

    public static Member createMember(String email) {
        return Member.register(createMemberRegisterRequest(email), createPasswordEncoder());
    }
}
