package com.mypractice.microservice.usermgtsvc.service.entity.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "permission")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    private String id;

    @Field(name = "name")
    @Indexed(unique = true)
    private String name;



}
