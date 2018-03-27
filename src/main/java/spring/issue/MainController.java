package spring.issue;

import lombok.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class MainController {

    @Value
    public static class Data {

        @NotNull
        private String a;
    }

    @PutMapping(value = "/streamed/{asd}")
    public Mono<Void> streamed(@PathVariable("asd") String asd, @RequestBody @Valid Mono<Data> body) {
        System.out.println(asd);

        body
                .map(x -> x.getA())
                .log("before")
                .subscribeOn(Schedulers.elastic())
                .log("after")
                .subscribe();

        return Mono.empty();
    }

}
