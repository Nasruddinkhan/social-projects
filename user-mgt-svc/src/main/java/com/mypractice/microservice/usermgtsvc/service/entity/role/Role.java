package com.mypractice.microservice.usermgtsvc.service.entity.role;

import com.mypractice.microservice.usermgtsvc.service.entity.permission.Permission;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Document(collection = "role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @EqualsAndHashCode.Include
    @Id
    private String id;
    @Field(name = "name", order = 2)
    @Indexed(unique = true)
    private String name;
    //later will add relationship becasue dbRef & document reference is not support
    private Set<Permission> permission;

}
