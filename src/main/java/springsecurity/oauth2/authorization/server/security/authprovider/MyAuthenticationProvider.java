package springsecurity.oauth2.authorization.server.security.authprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import springsecurity.oauth2.authorization.server.exceptions.BadKeyException;
import springsecurity.oauth2.authorization.server.security.authentication.CustomAuthentication;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Value("${key}")
    private String key;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication ca = (CustomAuthentication) authentication;

        String headerKey = ca.getKey();

        if (key.equals(headerKey)) {
            return new CustomAuthentication(true, null);
        }

        throw new BadKeyException("Key incorrect or not found");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
