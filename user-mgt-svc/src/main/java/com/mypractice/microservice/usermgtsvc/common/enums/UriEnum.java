package com.mypractice.microservice.usermgtsvc.common.enums;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum UriEnum {
    URI_BASE_PATH("/user-mgt/api"),
    URI_USERS("/v1/users"),
    URI_ALL_URLS("/v1/allUrls"),
    URI_PERMISSIONS("/v1/permissions"),
    URI_PERMISSIONS_BY_ID("/v1/permissions/{id}"),
    URI_ROLES("/v1/roles"),
    URI_ROLES_BY_ID("/v1/roles/{id}");
    private final String value;

    UriEnum(String value) {
        this.value = value;
    }
    public static final List<String> allUrls = Collections.unmodifiableList(
            Arrays.stream(UriEnum.values()).map(uri -> uri.value()).collect(Collectors.toList()));

    public String value() {
        return this.value;
    }
    @Override
    public String toString() {
        return this.value;
    }
}
