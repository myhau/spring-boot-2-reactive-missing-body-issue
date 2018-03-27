package spring.issue;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import spring.issue.MainController.Data;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ExtendWith(SpringExtension.class)
class MainControllerTest {

    @Autowired
    WebTestClient client;

    @RepeatedTest(5)
    public void test(){
        client
                .put()
                .uri("/streamed/asd")
                .body(BodyInserters.fromPublisher(data(), new ParameterizedTypeReference<Data>() {}))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    private Mono<Data> data() {
        return Mono.just(new Data("data"));
    }

}