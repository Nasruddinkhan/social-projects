package com.mypractice.microservice.usermgtsvc.config.exception;

import com.mypractice.microservice.socialcore.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.all;

@Component
@Slf4j
public class GlobalErrorHandling extends AbstractErrorWebExceptionHandler {
    public GlobalErrorHandling(ErrorAttributes errorAttributes, ApplicationContext applicationContext,
                               ServerCodecConfigurer serverCodecConfigurer) {

        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        // TODO Auto-generated constructor stub
        super.setMessageReaders(serverCodecConfigurer.getReaders());
        super.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(all(), this::renderErrorResponse);
    }

    public Mono<ServerResponse> renderErrorResponse(ServerRequest request) {

        final var errMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        final var errors = buildErrors(getError(request), errMap);
        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errors));

    }

    private static ErrorDto buildErrors(Throwable ex, Map<String, Object> errMap) {
        return ErrorDto.builder()
                .timestamp((Date) errMap.get("timestamp"))
                .path(String.valueOf(errMap.get("path")))
                .status(errMap.get("status"))
                .error(String.valueOf(errMap.get("error")))
                .requestId(String.valueOf(errMap.get("requestId")))
                .details(ex.getMessage())
                .build();
    }
}
