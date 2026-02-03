package pcy.study.tobyspringboot.hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloApiTest {

    @Test
    void helloApi() {
        // given
        RestClient client = RestClient.create();

        // when
        ResponseEntity<String> response = client.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("localhost")
                        .port(9090)
                        .path("/app/hello")
                        .queryParam("name", "Spring")
                        .build())
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
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("localhost")
                        .port(9090)
                        .path("/app/hello")
                        .queryParam("name", (String) null)
                        .build())
                .exchange((req, res) ->
                        ResponseEntity
                                .status(res.getStatusCode())
                                .body(res.bodyTo(String.class))
                );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
