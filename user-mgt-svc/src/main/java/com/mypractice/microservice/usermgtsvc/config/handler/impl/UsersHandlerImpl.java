package com.mypractice.microservice.usermgtsvc.config.handler.impl;

import com.mypractice.microservice.socialcore.dto.UserDto;
import com.mypractice.microservice.usermgtsvc.config.handler.UsersHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service("usersHandler")
public class UsersHandlerImpl implements UsersHandler {
    @Override
    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        final var item = serverRequest.bodyToMono(UserDto.class);
        return ServerResponse.ok().body(item, UserDto.class);
    }

    @Override
    public Mono<ServerResponse> getUsers(ServerRequest serverRequest) {
        System.out.println("UsersHandlerImpl.getUsers");
        return ServerResponse.ok().body(Flux.empty(), UserDto.class);
    }
}
