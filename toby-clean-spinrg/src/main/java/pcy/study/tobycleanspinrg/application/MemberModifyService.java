package pcy.study.tobycleanspinrg.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pcy.study.tobycleanspinrg.application.provided.MemberFinder;
import pcy.study.tobycleanspinrg.application.provided.MemberRegister;
import pcy.study.tobycleanspinrg.application.required.EmailSender;
import pcy.study.tobycleanspinrg.application.required.MemberRepository;
import pcy.study.tobycleanspinrg.domain.DuplicateEmailException;
import pcy.study.tobycleanspinrg.domain.Email;
import pcy.study.tobycleanspinrg.domain.Member;
import pcy.study.tobycleanspinrg.domain.PasswordEncoder;
import pcy.study.tobycleanspinrg.domain.request.MemberRegisterRequest;

@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class MemberModifyService implements MemberRegister {

    private final MemberFinder memberFinder;
    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member register(MemberRegisterRequest registerRequest) {
        checkDuplicateEmail(registerRequest);
        Member member = Member.register(registerRequest, passwordEncoder);
        memberRepository.save(member);
        sendWelcomeEmail(member);
        return member;
    }

    @Override
    public Member activate(Long memberId) {
        Member member = memberFinder.find(memberId);
        member.activate();
        memberRepository.save(member);
        return member;
    }

    private void checkDuplicateEmail(MemberRegisterRequest registerRequest) {
        Email email = new Email(registerRequest.email());
        if(memberRepository.findByEmail(email).isPresent()) {
            throw new DuplicateEmailException("이미 사용 중인 이메일입니다: " + registerRequest.email());
        }
    }

    private void sendWelcomeEmail(Member member) {
        emailSender.send(member.getEmail(), "등록을 완료해 주세요.", "링크를 클릭해서 등록을 완료해 주세요.");
    }
}
