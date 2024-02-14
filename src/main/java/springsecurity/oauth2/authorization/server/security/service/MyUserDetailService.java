package springsecurity.oauth2.authorization.server.security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import springsecurity.oauth2.authorization.server.domain.User;
import springsecurity.oauth2.authorization.server.repository.UserRepository;
import springsecurity.oauth2.authorization.server.security.entities.SecurityUserDetails;

import java.util.Optional;
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByName(username);

        return user.map(SecurityUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}