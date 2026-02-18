package pcy.study.tobycleanspinrg.domain.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProfileTest {

    @Test
    void profileFail() {
        // when
        // then
        assertThatThrownBy(() -> new Profile("0123456789123456"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void url() {
        // given
        var profile = new Profile("toby");

        // when
        String result = profile.url();

        // then
        assertThat(result).isEqualTo("@toby");
    }
}
