package springsecurity.oauth2.authorization.server.exceptions;

import org.springframework.security.core.AuthenticationException;

public class BadKeyException extends AuthenticationException {
    public BadKeyException(String msg) {
        super(msg);
    }
}
