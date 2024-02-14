package springsecurity.oauth2.authorization.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springsecurity.oauth2.authorization.server.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByName(String username);
}
