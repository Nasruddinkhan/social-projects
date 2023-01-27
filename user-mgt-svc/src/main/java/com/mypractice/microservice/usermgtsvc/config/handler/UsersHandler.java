package com.mypractice.microservice.usermgtsvc.config.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface UsersHandler {
    Mono<ServerResponse> createUser(ServerRequest serverRequest);
    Mono<ServerResponse> getUsers(ServerRequest serverRequest);

}
