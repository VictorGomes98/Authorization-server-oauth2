package springsecurity.oauth2.authorization.server.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springsecurity.oauth2.authorization.server.security.authentication.CustomAuthentication;
import springsecurity.oauth2.authorization.server.security.managers.MyAuthenticationManager;

import java.io.IOException;

@AllArgsConstructor
@Component
public class MyFilter extends OncePerRequestFilter {
    private final MyAuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String key = String.valueOf(request.getHeader("key"));               //
        CustomAuthentication authentication = new CustomAuthentication(false, key); // authentication that is not yet authenticated

        Authentication auth = authenticationManager.authenticate(authentication);

        if (auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        }
    }
}