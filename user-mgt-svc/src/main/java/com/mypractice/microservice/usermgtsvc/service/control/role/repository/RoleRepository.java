package com.mypractice.microservice.usermgtsvc.service.control.role.repository;

import com.mypractice.microservice.usermgtsvc.service.entity.role.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ReactiveMongoRepository<Role, String> {
}
