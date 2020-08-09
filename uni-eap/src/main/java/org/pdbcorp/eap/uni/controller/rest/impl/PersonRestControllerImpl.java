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

import java.util.Collection;

import javax.ws.rs.core.Response;

import org.pdbcorp.eap.uni.controller.rest.PersonRestController;
import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.service.details.impl.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author jaradat-pdb
 */
@Controller
public class PersonRestControllerImpl implements PersonRestController {

	private PersonDetailsService personDetailsService;

	@Autowired
	public PersonRestControllerImpl(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}

	@Override
	public Collection<Person> findAll() {
		return personDetailsService.findAll();
	}

	@Override
	public Response findByFname(String fname) {
		return Response.ok(personDetailsService.findByFname(fname)).build();
	}

	@Override
	public Response savePerson(Person person) {
		return Response.ok(personDetailsService.validateAndSave(person)).build();
	}

}
