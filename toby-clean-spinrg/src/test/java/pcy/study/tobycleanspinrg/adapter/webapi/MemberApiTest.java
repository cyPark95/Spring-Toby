package pcy.study.tobycleanspinrg.adapter.webapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.tobycleanspinrg.adapter.webapi.dto.MemberRegisterResponse;
import pcy.study.tobycleanspinrg.application.member.provided.MemberRegister;
import pcy.study.tobycleanspinrg.application.member.required.MemberRepository;
import pcy.study.tobycleanspinrg.domain.member.Member;
import pcy.study.tobycleanspinrg.domain.member.MemberStatus;
import pcy.study.tobycleanspinrg.domain.member.request.MemberRegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static pcy.study.tobycleanspinrg.AssertThatUtils.equalsTo;
import static pcy.study.tobycleanspinrg.AssertThatUtils.notNull;
import static pcy.study.tobycleanspinrg.domain.member.MemberFixture.createMemberRegisterRequest;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
public class MemberApiTest {

    private final MockMvcTester mvcTester;
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final MemberRegister memberRegister;

    @Test
    void register() throws Exception {
        // given
        MemberRegisterRequest request = createMemberRegisterRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        // when
        MvcTestResult result = mvcTester.post().uri("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson).exchange();

        // then
        assertThat(result)
                .hasStatusOk()
                .bodyJson()
                .hasPathSatisfying("$.memberId", notNull())
                .hasPathSatisfying("$.email", equalsTo(request.email()));

        MemberRegisterResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), MemberRegisterResponse.class);

        Member member = memberRepository.findById(response.memberId()).orElseThrow();
        assertThat(member.getEmail().address()).isEqualTo(request.email());
        assertThat(member.getNickname()).isEqualTo(request.nickname());
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void duplicateEmail() throws Exception {
        // given
        memberRegister.register(createMemberRegisterRequest());

        MemberRegisterRequest request = createMemberRegisterRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        // when
        MvcTestResult result = mvcTester.post().uri("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson).exchange();

        // then
        assertThat(result)
                .apply(print())
                .hasStatus(HttpStatus.CONFLICT);
    }
}
