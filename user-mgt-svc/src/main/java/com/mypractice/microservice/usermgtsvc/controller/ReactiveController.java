package com.mypractice.microservice.usermgtsvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RestController
public class ReactiveController {

    @GetMapping("/flux")
    public Flux<Integer> returnFlux() {
        log.info("ReactiveController.returnFlux");
        return Flux.just(1, 2, 3, 4, 5, 6).log();
    }

    @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> returnFluxStream() {
        return Flux.interval(Duration.ofSeconds(1)).log();
    }

    @GetMapping("/mono")
    public Mono<Integer> returnMono() {
        return Mono.just(1).log();
    }


}