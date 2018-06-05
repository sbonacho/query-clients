package com.sbonacho.seda.examples.insurance.persistence;

import com.sbonacho.seda.examples.insurance.model.ClientPorfolio;

import java.util.List;
import java.util.UUID;

public interface ClientRepository {
    List<ClientPorfolio> findAll();
    ClientPorfolio findById(UUID id);
    ClientPorfolio create(ClientPorfolio clientPorfolio);
    ClientPorfolio update(ClientPorfolio clientPorfolio);
    ClientPorfolio delete(ClientPorfolio clientPorfolio);
}
