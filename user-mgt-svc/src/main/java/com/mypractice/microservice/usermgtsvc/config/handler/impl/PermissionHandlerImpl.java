package com.mypractice.microservice.usermgtsvc.config.handler.impl;

import com.mypractice.microservice.socialcore.dto.permission.PermissionDto;
import com.mypractice.microservice.usermgtsvc.config.handler.PermissionHandler;
import com.mypractice.microservice.usermgtsvc.service.control.permission.helper.PermissionHelper;
import com.mypractice.microservice.usermgtsvc.service.control.permission.repository.PermissionRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service("permissionHandler")
@Observed(name = "permissionHandler")
public class PermissionHandlerImpl implements PermissionHandler {
    private final PermissionRepository permissionRepository;

    @Override
    public Mono<ServerResponse> createPermission(ServerRequest serverRequest) {
        log.info("PermissionHandlerImpl.createPermission start");
        final var records = serverRequest.bodyToMono(PermissionDto.class).log()
                .map(PermissionHelper::convertDTOToEntity)
                .flatMap(permissionRepository::save)
                .map(PermissionHelper::convertEntityToDTO).log();
        log.info("PermissionHandlerImpl.createPermission end");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(records, PermissionDto.class);
    }

    @Override
    public Mono<ServerResponse> findPermissions(ServerRequest serverRequest) {
        log.info("PermissionHandlerImpl.findPermissions start");
        final var permissions = permissionRepository.findAll()
                .map(PermissionHelper::convertEntityToDTO).log();
        log.info("PermissionHandlerImpl.findPermissions end");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(permissions, PermissionDto.class);
    }

    @Override
    public Mono<ServerResponse> findPermissionById(ServerRequest serverRequest) {
        log.info("PermissionHandlerImpl.findPermissionById start");
        final var permissionId = serverRequest.pathVariable("id");
        final var permission = Optional.ofNullable(permissionId).
                map(id -> permissionRepository.findById(id)
                        .map(PermissionHelper::convertEntityToDTO)
                        .switchIfEmpty(Mono.error(new RuntimeException("passing invalid id"))))
                .orElseThrow(() -> new RuntimeException("request parameter is not present"));
        log.info("PermissionHandlerImpl.findPermissionById end");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(permission, PermissionDto.class);

    }


    @Override
    public Mono<ServerResponse> updatePermissions(ServerRequest serverRequest) {
        log.info("PermissionHandlerImpl.updatePermissions start");
        final var permissionId = serverRequest.queryParam("id")
                .orElseThrow(() -> new RuntimeException("request parameter is not present"));
        final var permissionDto = serverRequest.bodyToMono(PermissionDto.class);
        final var permissionObj = permissionRepository.findById(permissionId)
                .flatMap(e -> PermissionHelper.mapEntityToDto(e, permissionDto))
                .flatMap(permissionRepository::save)
                .map(PermissionHelper::convertEntityToDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("passing invalid id")));
        log.info("PermissionHandlerImpl.updatePermissions end");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(permissionObj, PermissionDto.class);
    }

    @Override
    public Mono<ServerResponse> deletePermissions(ServerRequest serverRequest) {
        log.info("PermissionHandlerImpl.deletePermissions start");
        final var permissionId = serverRequest.queryParam("id")
                .orElseThrow(() -> new RuntimeException("request parameter is not present"));
        permissionRepository.findById(permissionId)
                .log()
                .flatMap(e -> permissionRepository.deleteById(e.getId())).log().switchIfEmpty(Mono.error(new RuntimeException("passing invalid id")));
        log.info("PermissionHandlerImpl.deletePermissions end");
        return ServerResponse.noContent().build();
    }
}
