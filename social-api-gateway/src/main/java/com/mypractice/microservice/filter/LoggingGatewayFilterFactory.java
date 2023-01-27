package com.mypractice.microservice.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {


    public static final String BASE_MSG = "baseMessage";
    public static final String PRE_LOGGER = "preLogger";
    public static final String POST_LOGGER = "postLogger";

    public LoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of(BASE_MSG, PRE_LOGGER, POST_LOGGER);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            if (config.isPreLogger()){
                log.info("Pre GatewayFilter logging: " + config.getBaseMessage());

            }
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        if (config.isPostLogger())
                            log.info("Post GatewayFilter logging: " + config.getBaseMessage());
                    }));
        }, 1);
    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

    }
}