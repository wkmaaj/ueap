/*
 * Copyright 2020 PDB Corp.
 *
 * Proprietary Software built off of open-source software?
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pdbcorp.eap.uni.controller.rest.impl;

import javax.ws.rs.core.Response;

import org.pdbcorp.eap.uni.controller.rest.PersonRestController;
import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.process.flow.AddEntityProcessFlow;
import org.pdbcorp.eap.uni.service.retrieve.impl.PersonDetailsRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;

/**
 * 
 * @author jaradat-pdb
 */
@Controller
public class PersonRestControllerImpl implements PersonRestController {

	private PersonDetailsRetrieverService personDetailsRetrieverService;
	private AddEntityProcessFlow<Person> addPersonProcessFlow;

	@Autowired
	public PersonRestControllerImpl(
			PersonDetailsRetrieverService personDetailsRetrieverService,
			@Qualifier("addPersonProcessFlow") AddEntityProcessFlow<Person> addPersonProcessFlow) {
		
		this.personDetailsRetrieverService = personDetailsRetrieverService;
		this.addPersonProcessFlow = addPersonProcessFlow;
	}

	@Override
	public Flux<Person> findAll() {
		return personDetailsRetrieverService.findAll();
	}

	@Override
	public Response findByFname(String fname) {
		return Response.ok(personDetailsRetrieverService.findByFname(fname)).build();
	}

	@Override
	public Response savePerson(Person person) {
		return Response.ok(addPersonProcessFlow.execute(person)).build();
	}

}
