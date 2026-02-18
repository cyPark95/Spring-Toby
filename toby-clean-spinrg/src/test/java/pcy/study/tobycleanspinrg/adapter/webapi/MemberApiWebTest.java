package pcy.study.tobycleanspinrg.adapter.webapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import pcy.study.tobycleanspinrg.application.member.provided.MemberRegister;
import pcy.study.tobycleanspinrg.domain.member.Member;
import pcy.study.tobycleanspinrg.domain.member.request.MemberRegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pcy.study.tobycleanspinrg.domain.member.MemberFixture.createMember;
import static pcy.study.tobycleanspinrg.domain.member.MemberFixture.createMemberRegisterRequest;

@WebMvcTest(MemberWebApi.class)
@RequiredArgsConstructor
class MemberApiWebTest {

    private final MockMvcTester mvcTester;
    private final ObjectMapper objectMapper;

    @MockitoBean
    private MemberRegister memberRegister;

    @Test
    void register() throws Exception {
        // given
        Member member = createMember(1L);
        when(memberRegister.register(any())).thenReturn(member);

        MemberRegisterRequest request = createMemberRegisterRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        // when
        // then
        assertThat(
                mvcTester.post().uri("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        )
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.memberId").asNumber().isEqualTo(1);

        // then
        verify(memberRegister).register(request);
    }

    @Test
    void registerFail() throws Exception {
        // given
        MemberRegisterRequest request = createMemberRegisterRequest("invalid email");
        String requestJson = objectMapper.writeValueAsString(request);

        // when
        // then
        assertThat(
                mvcTester.post().uri("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        )
                .hasStatus(HttpStatus.BAD_REQUEST);
    }
}
