package com.mypractice.microservice.usermgtsvc.config.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface RoleHandler {
    Mono<ServerResponse> createRole(ServerRequest serverRequest);

    Mono<ServerResponse> findRoleById(ServerRequest serverRequest);

    Mono<ServerResponse> findAllRole(ServerRequest serverRequest);

    Mono<ServerResponse> updateRole(ServerRequest serverRequest);

    Mono<ServerResponse> deleteRole(ServerRequest serverRequest);
}
