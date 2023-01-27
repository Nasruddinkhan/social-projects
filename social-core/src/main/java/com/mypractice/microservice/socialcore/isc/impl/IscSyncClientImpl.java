package com.mypractice.microservice.socialcore.isc.impl;

import com.mypractice.microservice.socialcore.isc.IscSyncClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component("iscSyncClient")
public record IscSyncClientImpl(WebClient client) implements IscSyncClient {


    @Override
    public <T> Mono<T> getObject(String serviceUrl, Map<String, String> requestHeadersMap, Class<T> type) throws RuntimeException {
        return client.get()
                .uri(serviceUrl)
                .retrieve()
                .bodyToMono(type);
    }

}
