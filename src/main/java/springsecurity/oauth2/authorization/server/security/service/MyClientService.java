package springsecurity.oauth2.authorization.server.security.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import springsecurity.oauth2.authorization.server.domain.Client;
import springsecurity.oauth2.authorization.server.exceptions.ClientNotFoundException;
import springsecurity.oauth2.authorization.server.repository.ClientRepository;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class MyClientService implements RegisteredClientRepository {
    private ClientRepository clientRepository;
    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(Client.from(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        Optional<Client> client = clientRepository.findById(Long.valueOf(id));

        if (client.isPresent()) {
            return Client.from(client.get());
        }
        throw new ClientNotFoundException("Client can't be found");
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Optional<Client> client = clientRepository.findByClientId(clientId);

        if (client.isPresent()) {
            return Client.from(client.get());
        }
        throw new ClientNotFoundException("Client Id can't be found");
    }
}
