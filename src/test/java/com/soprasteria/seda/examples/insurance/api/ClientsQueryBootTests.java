package com.soprasteria.seda.examples.insurance.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprasteria.seda.examples.insurance.events.ClientPortfolioCompleted;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = { ClientsQueryBootTests.saga })
public class ClientsQueryBootTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsQueryBootTests.class);

    @Autowired
    private MockMvc mockMvc;

    protected static final String saga = "saga";

	private KafkaTemplate<String, Object> kafkaTemplate;

	private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private KafkaEmbedded embeddedKafka;


    @Before
    public void setUp() throws Exception {
        // set up the Kafka producer properties
        Map<String, Object> senderProperties = KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
        senderProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        senderProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        ProducerFactory<String, Object> producerFactory = new DefaultKafkaProducerFactory<String, Object>(senderProperties);

        kafkaTemplate = new KafkaTemplate<>(producerFactory);
    }

    @Test
	public void clientCreateCompletedStoreQuery() throws Exception {

        ClientPortfolioCompleted completed = mapper.readValue("{\"type\": \"ClientCreated\", \"name\": \"John Doe\", \"address\": \"Bendford st 10\", \"interest\": \"Microservices\", \"portfolioId\":\"ccaaf032-32fe-4476-9ef2-9a70d18a58b0\",\"clientId\":\"82565d32-45ea-40c5-9f56-9d2a93a648a0\"}", ClientPortfolioCompleted.class);

		// Send mocked events --------------------

        Thread.sleep(100);
		kafkaTemplate.send(saga, completed);
        Thread.sleep(100);

        // Check if client is in DataBase
        mockMvc.perform(get("/client")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(completed.getAddress())));
    }

}
