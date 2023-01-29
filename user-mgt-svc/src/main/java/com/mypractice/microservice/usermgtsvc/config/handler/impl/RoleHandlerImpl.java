package com.mypractice.microservice.usermgtsvc.config.handler.impl;

import com.mypractice.microservice.socialcore.dto.permission.PermissionDto;
import com.mypractice.microservice.socialcore.dto.role.RoleDto;
import com.mypractice.microservice.usermgtsvc.config.handler.RoleHandler;
import com.mypractice.microservice.usermgtsvc.service.control.permission.helper.PermissionHelper;
import com.mypractice.microservice.usermgtsvc.service.control.role.helper.RoleHelper;
import com.mypractice.microservice.usermgtsvc.service.control.role.repository.RoleRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Observed(name = "roleHandler")
@Service("roleHandler")
public class RoleHandlerImpl implements RoleHandler {
    private final RoleRepository roleRepository;
    @Override
    public Mono<ServerResponse> createRole(ServerRequest serverRequest) {
        log.info("RoleHandlerImpl.createRole start");
        final var roleDtoMono = serverRequest.bodyToMono(RoleDto.class).log()
                .map(RoleHelper::convertDTOToEntity)
                .flatMap(roleRepository::save)
                .map(RoleHelper::convertEntityToDTO).log();
        log.info("RoleHandlerImpl.createRole end");
        return ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(roleDtoMono, RoleDto.class);
    }

    @Override
    public Mono<ServerResponse> findRoleById(ServerRequest serverRequest) {
        log.info("RoleHandlerImpl.findRoleById start");
        final var roleId = serverRequest.pathVariable("id");
        final var roles = Optional.ofNullable(roleId).
                map(id -> roleRepository.findById(id)
                        .log()
                        .map(RoleHelper::convertEntityToDTO)
                        .switchIfEmpty(Mono.error(new RuntimeException("passing invalid role id"))))
                .orElseThrow(() -> new RuntimeException("request parameter is not present"));
        log.info("RoleHandlerImpl.findRoleById end");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(roles, RoleDto.class);
    }

    @Override
    public Mono<ServerResponse> findAllRole(ServerRequest serverRequest) {
        log.info("RoleHandlerImpl.findAllRole start");
        final var roles = roleRepository.findAll()
                .map(RoleHelper::convertEntityToDTO).log();
        log.info("RoleHandlerImpl.findAllRole end");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(roles, RoleDto.class);
    }

    @Override
    public Mono<ServerResponse> updateRole(ServerRequest serverRequest) {
        log.info("RoleHandlerImpl.updateRole start");
        final var roleId = serverRequest.queryParam("id")
                .orElseThrow(() -> new RuntimeException("request parameter is not present"));
        final var roleDtoMono = serverRequest.bodyToMono(RoleDto.class);
        final var roles = roleRepository.findById(roleId)
                .flatMap(e -> RoleHelper.mapEntityToDto(e, roleDtoMono))
                .flatMap(roleRepository::save)
                .map(RoleHelper::convertEntityToDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("passing invalid id")));
        log.info("PermissionHandlerImpl.updateRole end");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(roles, RoleDto.class);
    }

    @Override
    public Mono<ServerResponse> deleteRole(ServerRequest serverRequest) {
        log.info("RoleHandlerImpl.deleteRole start");
        final var roleId = serverRequest.queryParam("id")
                .orElseThrow(() -> new RuntimeException("request parameter is not present"));
        roleRepository.findById(roleId)
                .log()
                .flatMap(e -> roleRepository.deleteById(e.getId())).log()
        .switchIfEmpty(Mono.error(new RuntimeException("passing invalid role id")));
        log.info("PermissionHandlerImpl.deleteRole end");
        return ServerResponse.noContent().build();
    }
}
