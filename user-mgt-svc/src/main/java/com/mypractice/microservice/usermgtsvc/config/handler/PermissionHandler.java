package com.mypractice.microservice.usermgtsvc.config.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface PermissionHandler {
    Mono<ServerResponse> createPermission(ServerRequest serverRequest);
    Mono<ServerResponse> findPermissions(ServerRequest serverRequest);
    Mono<ServerResponse> findPermissionById(ServerRequest serverRequest);
    Mono<ServerResponse> updatePermissions(ServerRequest serverRequest);
    Mono<ServerResponse> deletePermissions(ServerRequest serverRequest);
}
