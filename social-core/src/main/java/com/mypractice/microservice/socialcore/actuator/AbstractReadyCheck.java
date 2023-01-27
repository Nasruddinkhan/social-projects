package com.mypractice.microservice.socialcore.actuator;

import com.google.gson.JsonParser;
import com.mypractice.microservice.socialcore.constants.ApplicationConstant;
import com.mypractice.microservice.socialcore.dto.Error;
import com.mypractice.microservice.socialcore.dto.Ready;
import com.mypractice.microservice.socialcore.isc.IscSyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Endpoint(id = "ready")
public abstract class AbstractReadyCheck   {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReadyCheck.class);
    public static final String UP = "UP";
    @Autowired
    private IscSyncClient iscSyncClient;
    private boolean isUp(String response) {
        final var status = JsonParser.parseString(response).getAsJsonObject().get("status").getAsString();
        if (UP.equalsIgnoreCase(status) || status.equals(Integer.toString(HttpStatus.OK.value())) ) {
            return true;
        }
        return false;
    }

    public abstract Map<String, String> dependentComponents();

    @ReadOperation
    public Mono<Ready> getReadyStatus() {

        return Flux.fromIterable(this.dependentComponents().entrySet())
                .flatMap(entry ->
                        Mono.just(entry.getKey()) // Mono<Component>
                                .zipWith(getComponentStatus(entry.getValue()) // Get Mono<Status> of
                                        // component by url
                                ) // Glues two Monos above to Mono<Tuple<Component, Status>>
                )
                .collectMap(Tuple2::getT1, Tuple2::getT2)
                .map(readyData -> {
                    HttpStatus status;
                    if (readyData.containsValue(ApplicationConstant.NOT_READY)) {
                        status = HttpStatus.SERVICE_UNAVAILABLE;
                    } else {
                        status = HttpStatus.OK;
                    }
                    List<Error> notReadyComponent = readyData.entrySet().stream()
                            .filter(e -> ApplicationConstant.NOT_READY.equals(e.getValue()))
                            .map(e -> Error.builder()
                                    .message(e.getKey()+ApplicationConstant.COMPONENT_NOT_INITIALIZED)
                                    .code(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                                    .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                                    .build())
                            .collect(Collectors.toList());

                   // notReadyComponent, status, readyData
                    return  Ready.builder()
                            .errors(notReadyComponent)
                            .status(status.value())
                            .data(readyData)
                            .metadata(new LinkedHashMap<>())
                            .build();
                });

    }

    private Mono<String> getComponentStatus(String url) {
        return iscSyncClient.getObject(url, Collections.emptyMap(), String.class)
                .map(this::isUp).map(bool -> {
                    if (bool) {
                        return ApplicationConstant.READY;
                    } else {
                        return ApplicationConstant.NOT_READY;
                    }
                }).doOnError(err -> LOGGER.warn("Error on ready check for " + url, err))
                .onErrorReturn(ApplicationConstant.NOT_READY);
    }
}
