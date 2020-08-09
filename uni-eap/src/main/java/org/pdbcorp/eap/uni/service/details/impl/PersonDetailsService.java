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
package org.pdbcorp.eap.uni.service.details.impl;

import java.util.Collection;

import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.data.repo.PersonRepository;
import org.pdbcorp.eap.uni.service.persist.EntityPersistenceService;
import org.pdbcorp.eap.uni.service.validation.EntityValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Slf4j
@Service
public class PersonDetailsService extends BaseEntityDetailsService<Person> {

	private PersonRepository personRepository;
	private EntityPersistenceService<Person> personPersistenceService;

	@Autowired
	public PersonDetailsService(PersonRepository personRepository,
			@Qualifier("personValidationService") EntityValidationService<Person> personValidationService,
			@Qualifier("personPersistenceService") EntityPersistenceService<Person> personPersistenceService) {
		
		super(personRepository, personValidationService);
		this.personRepository = personRepository;
		this.personPersistenceService = personPersistenceService;
	}

	@Override
	public Person validateAndSave(Person person) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", person);
		}
		person = super.validateAndSave(person);
		person = personPersistenceService.validateEntityRelationships(person);
		person = personRepository.save(person);
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", person);
		}
		return person;
	}

	public Collection<Person> findByFname(String fname) {
		return personRepository.findByFname(fname);
	}

}