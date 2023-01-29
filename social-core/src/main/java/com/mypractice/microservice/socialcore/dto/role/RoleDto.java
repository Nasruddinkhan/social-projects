package com.mypractice.microservice.socialcore.dto.role;

import com.mypractice.microservice.socialcore.dto.permission.PermissionDto;

import java.util.Set;

public record RoleDto(String id, String name, Set<PermissionDto> permissions) {
}
