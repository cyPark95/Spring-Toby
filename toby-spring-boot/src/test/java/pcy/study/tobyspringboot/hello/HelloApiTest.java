package pcy.study.tobyspringboot.hello;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {

    @Test
    void helloApi() {
        // given
        RestClient client = RestClient.create();

        // when
        ResponseEntity<String> response = client.get()
                .uri("http://localhost:8080/hello?name={name}", "Spring")
                .retrieve()
                .toEntity(String.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType().toString()).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(response.getBody()).isEqualTo("Hello Spring");
    }

    @Test
    void failsHelloApi() {
        // given
        RestClient client = RestClient.create();

        // when
        ResponseEntity<String> response = client.get()
                .uri("http://localhost:8080/hello?name={name}")
                .retrieve()
                .toEntity(String.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
