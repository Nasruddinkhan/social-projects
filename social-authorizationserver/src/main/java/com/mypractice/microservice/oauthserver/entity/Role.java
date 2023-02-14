package com.mypractice.microservice.oauthserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1905122041950251208L;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "permission_role", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "id") })
    private  List<Permission> permissions;


}