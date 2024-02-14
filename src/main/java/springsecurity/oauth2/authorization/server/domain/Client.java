package springsecurity.oauth2.authorization.server.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

@Data
@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;
    private String secret;
    private String redirectUri;
    private String scope;
    private String authMethod;
    private String grantType;

    public static Client from(RegisteredClient registeredClient) {
        Client client = new Client();

        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());
        client.setRedirectUri(registeredClient.getRedirectUris().stream().findAny().toString());
        client.setScope(registeredClient.getScopes().stream().findAny().toString());
        client.setAuthMethod(registeredClient.getClientAuthenticationMethods().stream().findAny().toString());
        client.setGrantType(registeredClient.getAuthorizationGrantTypes().stream().findAny().toString());

        return client;
    }

    public static RegisteredClient from(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .scope(client.getScope())
                .redirectUri(client.getRedirectUri())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthMethod()))
                .authorizationGrantType(new AuthorizationGrantType(client.getGrantType()))
                .build();
    }
}
