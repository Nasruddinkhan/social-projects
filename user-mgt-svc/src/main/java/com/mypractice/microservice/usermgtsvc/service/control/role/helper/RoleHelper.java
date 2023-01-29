package com.mypractice.microservice.usermgtsvc.service.control.role.helper;

import com.mypractice.microservice.socialcore.dto.permission.PermissionDto;
import com.mypractice.microservice.socialcore.dto.role.RoleDto;
import com.mypractice.microservice.usermgtsvc.service.control.permission.helper.PermissionHelper;
import com.mypractice.microservice.usermgtsvc.service.entity.role.Role;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

public class RoleHelper {
    public static Role convertDTOToEntity(final RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.id())
                .name(roleDto.name())
                .permission(roleDto.permissions().stream().map(PermissionHelper::convertDTOToEntity).collect(Collectors.toSet()))
                .build();
    }

    public static RoleDto convertEntityToDTO(final Role role) {
        return new RoleDto(role.getId(), role.getName(), role.getPermission().stream().map(PermissionHelper::convertEntityToDTO).collect(Collectors.toSet()));
    }

    public static Mono<Role> mapEntityToDto(Role role, Mono<RoleDto> roleDto) {
        return roleDto.map(e-> {
            role.setName(e.name());
            role.setPermission(e.permissions().stream().map(PermissionHelper::convertDTOToEntity).collect(Collectors.toSet()));
            return role;
        });
    }
}
