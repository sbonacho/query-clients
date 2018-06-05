package com.sbonacho.seda.examples.insurance.api;

import com.sbonacho.seda.examples.insurance.persistence.ClientRepository;
import com.sbonacho.seda.examples.insurance.model.ClientPorfolio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/client")
@ExposesResourceFor(ClientPorfolio.class)
public class ClientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private EntityLinks entityLinks;

	@Autowired
	private ClientRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	HttpEntity<Resources<ClientPorfolio>> showClientPorfolio() {

		Resources<ClientPorfolio> resources = new Resources<>(this.repository.findAll());
		resources.add(this.entityLinks.linkToCollectionResource(ClientPorfolio.class));
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
}
