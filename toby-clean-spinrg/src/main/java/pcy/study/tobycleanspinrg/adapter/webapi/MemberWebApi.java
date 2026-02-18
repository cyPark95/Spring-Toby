package pcy.study.tobycleanspinrg.adapter.webapi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.tobycleanspinrg.adapter.webapi.dto.MemberRegisterResponse;
import pcy.study.tobycleanspinrg.application.member.provided.MemberRegister;
import pcy.study.tobycleanspinrg.domain.member.Member;
import pcy.study.tobycleanspinrg.domain.member.request.MemberRegisterRequest;

@RestController
@RequiredArgsConstructor
public class MemberWebApi {

    private final MemberRegister memberRegister;

    @PostMapping("/api/members")
    public MemberRegisterResponse register(@RequestBody @Valid MemberRegisterRequest request) {
        Member member = memberRegister.register(request);
        return MemberRegisterResponse.of(member);
    }
}
