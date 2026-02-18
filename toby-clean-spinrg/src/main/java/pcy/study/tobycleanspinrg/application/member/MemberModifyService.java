package pcy.study.tobycleanspinrg.application.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pcy.study.tobycleanspinrg.application.member.provided.MemberFinder;
import pcy.study.tobycleanspinrg.application.member.provided.MemberRegister;
import pcy.study.tobycleanspinrg.application.member.required.EmailSender;
import pcy.study.tobycleanspinrg.application.member.required.MemberRepository;
import pcy.study.tobycleanspinrg.domain.member.DuplicateEmailException;
import pcy.study.tobycleanspinrg.domain.shared.Email;
import pcy.study.tobycleanspinrg.domain.member.Member;
import pcy.study.tobycleanspinrg.domain.member.PasswordEncoder;
import pcy.study.tobycleanspinrg.domain.member.request.MemberRegisterRequest;

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
