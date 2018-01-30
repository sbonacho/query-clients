package com.soprasteria.seda.examples.insurance.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprasteria.seda.examples.insurance.events.ClientPortfolioCompleted;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1)
public class ClientsQueryBootTests {

    private static final String saga = "saga";

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private ObjectMapper mapper = new ObjectMapper();


	@Test
	public void clientCreateCompletedStoreQuery() throws Exception {

        ClientPortfolioCompleted completed = mapper.readValue("{\"type\": \"ClientCreated\", \"name\": \"John Doe\", \"address\": \"Bendford st 10\", \"interest\": \"Microservices\", \"portfolioId\":\"ccaaf032-32fe-4476-9ef2-9a70d18a58b0\",\"clientId\":\"82565d32-45ea-40c5-9f56-9d2a93a648a0\"}", ClientPortfolioCompleted.class);

		// Send mocked events --------------------

		kafkaTemplate.send(saga, completed);


	}

}
