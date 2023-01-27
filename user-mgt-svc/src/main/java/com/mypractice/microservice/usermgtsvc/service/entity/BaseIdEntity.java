package com.mypractice.microservice.usermgtsvc.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
public class BaseIdEntity {

    @Id
    public String id;


}