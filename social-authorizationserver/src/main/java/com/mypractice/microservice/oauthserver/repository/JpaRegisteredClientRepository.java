package com.mypractice.microservice.oauthserver.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypractice.microservice.oauthserver.entity.Client;
import com.mypractice.microservice.oauthserver.util.CommonUtil;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
public class JpaRegisteredClientRepository implements RegisteredClientRepository {
    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;

    public JpaRegisteredClientRepository(final ClientRepository clientRepository, final ObjectMapper objectMapper1) {
        this.objectMapper = objectMapper1;
        Assert.notNull(clientRepository, "clientRepository cannot be null");
        this.clientRepository = clientRepository;
        final var classLoader = JpaRegisteredClientRepository.class.getClassLoader();
        List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
        this.objectMapper.registerModules(securityModules);
        this.objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        Assert.notNull(registeredClient, "registeredClient cannot be null");
        this.clientRepository.save(toEntity(registeredClient));
    }

    @Override
    public RegisteredClient findById(final String id) {
        Assert.hasText(id, "id cannot be empty");
        return this.clientRepository.findById(id).map(this::toObject).orElse(null);
    }

    @Override
    public RegisteredClient findByClientId(final String clientId) {
        Assert.hasText(clientId, "clientId cannot be empty");
        return this.clientRepository.findByClientId(clientId).map(this::toObject).orElse(null);
    }

    private RegisteredClient toObject(final Client client) {
        final var builder = getRegistrationClientBuilder(client);
        final var clientSettingsMap = parseMap(client.getClientSettings());
        builder.clientSettings(ClientSettings.withSettings(clientSettingsMap).build());
        final var tokenSettingsMap = parseMap(client.getTokenSettings());
        builder.tokenSettings(TokenSettings.withSettings(tokenSettingsMap).build());
        return builder.build();
    }

    private RegisteredClient.Builder getRegistrationClientBuilder(final Client client) {
        final var clientAuthenticationMethods = CommonUtil.convertStringToSet(client.getClientAuthenticationMethods());
        final var authorizationGrantTypes = CommonUtil.convertStringToSet(client.getAuthorizationGrantTypes());
        final var redirectUris = CommonUtil.convertStringToSet(client.getRedirectUris());
        final var clientScopes = CommonUtil.convertStringToSet(client.getScopes());
        return getResisterClient(client,authorizationGrantTypes, redirectUris, clientScopes,clientAuthenticationMethods );
    }

    private RegisteredClient.Builder getResisterClient(final Client client,final Set<String> authorizationGrantTypes,final Set<String> redirectUris,final Set<String> clientScopes,final Set<String> clientAuthenticationMethods) {
        return RegisteredClient.withId(client.getId())
                .clientId(client.getClientId())
                .clientIdIssuedAt(client.getClientIdIssuedAt())
                .clientSecret(client.getClientSecret())
                .clientSecretExpiresAt(client.getClientSecretExpiresAt())
                .clientName(client.getClientName())
                .clientAuthenticationMethods(authenticationMethods -> getClientAuthenticationMethods(authenticationMethods, clientAuthenticationMethods ))
                .authorizationGrantTypes(grantTypes -> getGrantTypes(grantTypes, authorizationGrantTypes))
                .redirectUris(uris -> uris.addAll(redirectUris))
                .scopes(scopes -> scopes.addAll(clientScopes));
    }


    private void getGrantTypes(final Set<AuthorizationGrantType> grantTypes,final Set<String> authorizationGrantTypes) {

        grantTypes.addAll(authorizationGrantTypes
                            .stream()
                            .map(JpaRegisteredClientRepository::resolveAuthorizationGrantType)
                            .toList());
    }

    private void getClientAuthenticationMethods(final Set<ClientAuthenticationMethod> authenticationMethods,final Set<String> clientAuthenticationMethods) {
        authenticationMethods.addAll(clientAuthenticationMethods.stream()
                .map(JpaRegisteredClientRepository::resolveClientAuthenticationMethod)
                .collect(toList()));
    }


    private Client toEntity(final RegisteredClient registeredClient) {
        final var clientAuthenticationMethods = getClientMethods(registeredClient);
        final var authorizationGrantTypes = getAuthorizationGrantTypes(registeredClient);
        return getClient(registeredClient, authorizationGrantTypes, clientAuthenticationMethods);
    }

    private Client getClient(final RegisteredClient registeredClient,final List<String> authorizationGrantTypes,final List<String> clientAuthenticationMethods) {
        final var entity = new Client();
        entity.setId(registeredClient.getId());
        entity.setClientId(registeredClient.getClientId());
        entity.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt());
        entity.setClientSecret(registeredClient.getClientSecret());
        entity.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt());
        entity.setClientName(registeredClient.getClientName());
        entity.setClientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods));
        entity.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes));
        entity.setRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()));
        entity.setScopes(StringUtils.collectionToCommaDelimitedString(registeredClient.getScopes()));
        entity.setClientSettings(writeMap(registeredClient.getClientSettings().getSettings()));
        entity.setTokenSettings(writeMap(registeredClient.getTokenSettings().getSettings()));
        return entity;
    }

    private List<String> getAuthorizationGrantTypes(final RegisteredClient registeredClient) {
      return   registeredClient.getAuthorizationGrantTypes().stream()
                .map(AuthorizationGrantType::getValue)
                .collect(toList());
    }

    private List<String> getClientMethods(final RegisteredClient registeredClient) {
        return registeredClient.getClientAuthenticationMethods().stream()
                .map(ClientAuthenticationMethod::getValue)
                .collect(toList());
    }

    private Map<String, Object> parseMap(final String data) {
        try {
            return this.objectMapper.readValue(data, new TypeReference<>() {
            });
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    private String writeMap(final Map<String, Object> data) {
        try {
            return this.objectMapper.writeValueAsString(data);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    private static AuthorizationGrantType resolveAuthorizationGrantType(final String authorizationGrantType) {
        return switch (authorizationGrantType) {
            case "AUTHORIZATION_CODE" -> AuthorizationGrantType.AUTHORIZATION_CODE;
            case "CLIENT_CREDENTIALS" -> AuthorizationGrantType.CLIENT_CREDENTIALS;
            case "REFRESH_TOKEN" -> AuthorizationGrantType.REFRESH_TOKEN;
            default -> new AuthorizationGrantType(authorizationGrantType);
        };
    }

    private static ClientAuthenticationMethod resolveClientAuthenticationMethod(final String clientAuthenticationMethod) {
        return switch (clientAuthenticationMethod) {
            case "CLIENT_SECRET_BASIC" -> ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
            case "CLIENT_SECRET_POST" -> ClientAuthenticationMethod.CLIENT_SECRET_POST;
            case "NONE" -> ClientAuthenticationMethod.NONE;
            default -> new ClientAuthenticationMethod(clientAuthenticationMethod);
        };
    }
}

