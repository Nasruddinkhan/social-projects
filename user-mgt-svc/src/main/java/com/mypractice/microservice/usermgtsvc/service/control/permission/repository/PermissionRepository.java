package com.mypractice.microservice.usermgtsvc.service.control.permission.repository;

import com.mypractice.microservice.usermgtsvc.service.entity.Permission;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends ReactiveMongoRepository<Permission, String> {
}
