package com.mypractice.microservice.socialcore.isc;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface IscSyncClient {
    <T> Mono<T> getObject(String url, Map<String, String> emptyMap, Class<T> type);
}
