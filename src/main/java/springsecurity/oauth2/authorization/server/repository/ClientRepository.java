package springsecurity.oauth2.authorization.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springsecurity.oauth2.authorization.server.domain.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    public Optional<Client> findByClientId(String clientId);
}
