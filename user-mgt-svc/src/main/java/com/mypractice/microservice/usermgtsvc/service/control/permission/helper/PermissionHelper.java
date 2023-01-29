package com.mypractice.microservice.usermgtsvc.service.control.permission.helper;

import com.mypractice.microservice.socialcore.dto.permission.PermissionDto;
import com.mypractice.microservice.usermgtsvc.service.entity.permission.Permission;
import reactor.core.publisher.Mono;

public class PermissionHelper {

    public static Permission convertDTOToEntity(PermissionDto permissionDto) {
        return Permission.builder().id(permissionDto.id()).name(permissionDto.name()).build();

    }

    public static PermissionDto convertEntityToDTO(Permission permission) {
        return new PermissionDto(permission.getId(), permission.getName());
    }

    public static Mono<Permission> mapEntityToDto(Permission permission, Mono<PermissionDto> permissionDto) {
       return permissionDto.map(e-> {
            permission.setName(e.name());
            return permission;
        });

    }
}
