package com.sbonacho.seda.examples.insurance.bus.kafka.listeners;

import com.sbonacho.seda.examples.insurance.events.ClientPortfolioCompleted;
import com.sbonacho.seda.examples.insurance.model.ClientPorfolio;
import com.sbonacho.seda.examples.insurance.persistence.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClientsPortfolioListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsPortfolioListener.class);

    @Autowired
    private ClientRepository repository;

    @KafkaListener(topics = "${connector.topics.saga}")
    public void clientPortfolioCompleted(ClientPortfolioCompleted event) {
        LOGGER.info("ClientPortfolioCompleted Event Received -> {}", event);

        ClientPorfolio client = new ClientPorfolio();
        client.setId(event.getClientId());
        client.setAddress(event.getAddress());
        client.setInterest(event.getInterest());
        client.setName(event.getName());
        repository.create(client);
    }
}

