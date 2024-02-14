package springsecurity.oauth2.authorization.server.security.managers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import springsecurity.oauth2.authorization.server.security.authprovider.MyAuthenticationProvider;

@Component
@AllArgsConstructor
public class MyAuthenticationManager implements AuthenticationManager {

    private final MyAuthenticationProvider provider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (provider.supports(authentication.getClass())) {
            return provider.authenticate(authentication);
        }

        throw new BadCredentialsException("Invalid credentials");
    }
}
