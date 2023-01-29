package com.mypractice.microservice.usermgtsvc.config.routes;

import com.mypractice.microservice.usermgtsvc.common.enums.UriEnum;
import com.mypractice.microservice.usermgtsvc.config.handler.PermissionHandler;
import com.mypractice.microservice.usermgtsvc.config.handler.RoleHandler;
import com.mypractice.microservice.usermgtsvc.config.handler.UsersHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRoutes {

    @Bean
    public RouterFunction<ServerResponse> usersRoutes(final UsersHandler usersHandler, final PermissionHandler permissionHandler, final RoleHandler roleHandler) {
        System.out.println("UserRoutes.usersRoutes");
        return RouterFunctions.nest(path(UriEnum.URI_BASE_PATH.value()), route(RequestPredicates.POST(UriEnum.URI_USERS.value()).and(accept(MediaType.APPLICATION_JSON)), usersHandler::createUser)
                .andRoute(GET(UriEnum.URI_USERS.value()).and(accept(MediaType.APPLICATION_JSON)), usersHandler::getUsers)
                .andRoute(GET(UriEnum.URI_ALL_URLS.value()).and(accept(MediaType.APPLICATION_JSON)), this::allURLs)
                .andRoute(GET(UriEnum.URI_PERMISSIONS.value()).and(accept(MediaType.APPLICATION_JSON)), permissionHandler::findPermissions)
                .andRoute(GET(UriEnum.URI_PERMISSIONS_BY_ID.value()).and(accept(MediaType.APPLICATION_JSON)), permissionHandler::findPermissionById)
                .andRoute(PUT(UriEnum.URI_PERMISSIONS.value()).and(accept(MediaType.APPLICATION_JSON)), permissionHandler::updatePermissions)
                .andRoute(DELETE(UriEnum.URI_PERMISSIONS.value()).and(accept(MediaType.APPLICATION_JSON)), permissionHandler::deletePermissions)
                .andRoute(RequestPredicates.POST(UriEnum.URI_PERMISSIONS.value()).and(accept(MediaType.APPLICATION_JSON)), permissionHandler::createPermission)
                .andRoute(POST(UriEnum.URI_ROLES.value()).and(accept(MediaType.APPLICATION_JSON)), roleHandler::createRole)
                .andRoute(PUT(UriEnum.URI_ROLES.value()).and(accept(MediaType.APPLICATION_JSON)), roleHandler::updateRole)
                .andRoute(GET(UriEnum.URI_ROLES.value()).and(accept(MediaType.APPLICATION_JSON)), roleHandler::findAllRole)
                .andRoute(GET(UriEnum.URI_ROLES_BY_ID.value()).and(accept(MediaType.APPLICATION_JSON)), roleHandler::findRoleById)
                .andRoute(DELETE(UriEnum.URI_ROLES.value()).and(accept(MediaType.APPLICATION_JSON)), roleHandler::deleteRole)

        );
    }

    private Mono<ServerResponse> allURLs(ServerRequest serverRequest) {
        final var urls = Flux.fromIterable(UriEnum.allUrls);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(urls, new ParameterizedTypeReference<Flux<String>>() {
        });
    }

    @Bean
    public WebExceptionHandler exceptionHandler() {
        return (ServerWebExchange exchange, Throwable ex) -> {
            if (ex instanceof DuplicateKeyException) {
                exchange.getResponse().setStatusCode(HttpStatus.CONFLICT);
                return exchange.getResponse().setComplete();
            }
            return Mono.error(ex);
        };
    }
}
