package com.mypractice.microservice.oauthserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    private String name;

}