package com.sbonacho.seda.examples.insurance.persistence;

import com.sbonacho.seda.examples.insurance.model.ClientPorfolio;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientRepositoryInMemory implements ClientRepository {

    private Map<UUID, ClientPorfolio> store = new HashMap<>();

    @Override
    public List<ClientPorfolio> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public ClientPorfolio findById(UUID id) {
        return store.get(id);
    }

    @Override
    public ClientPorfolio create(ClientPorfolio clientPorfolio) {
        return store.put(clientPorfolio.getId(), clientPorfolio);
    }

    @Override
    public ClientPorfolio update(ClientPorfolio clientPorfolio) {
        return store.replace(clientPorfolio.getId(), clientPorfolio);
    }

    @Override
    public ClientPorfolio delete(ClientPorfolio clientPorfolio) {
        return store.remove(clientPorfolio.getId());
    }
}
