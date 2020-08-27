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
package org.pdbcorp.eap.uni.service.validate.impl;

import java.util.HashSet;
import java.util.Set;

import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.data.model.University;
import org.pdbcorp.eap.uni.service.generate.GenerateNodeUidService;
import org.pdbcorp.eap.uni.service.validate.ValidateEntityRelationshipsService;
import org.pdbcorp.eap.uni.service.validate.ValidateNodeUidService;
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
class AddressRelationshipsValidatorService implements ValidateEntityRelationshipsService<Address> {

	private GenerateNodeUidService<Person> personNodeUidGeneratorService;
	private ValidateNodeUidService<Person> personNodeUidValidatorService;
	private GenerateNodeUidService<University> universityNodeUidGeneratorService;
	private ValidateNodeUidService<University> universityNodeUidValidatorService;

	@Autowired
	AddressRelationshipsValidatorService(
			@Qualifier("personNodeUidGeneratorService") GenerateNodeUidService<Person> personNodeUidGeneratorService,
			@Qualifier("personNodeUidValidatorService") ValidateNodeUidService<Person> personNodeUidValidatorService,
			@Qualifier("universityNodeUidGeneratorService") GenerateNodeUidService<University> universityNodeUidGeneratorService,
			@Qualifier("universityNodeUidValidatorService") ValidateNodeUidService<University> universityNodeUidValidatorService) {
		
		this.personNodeUidGeneratorService = personNodeUidGeneratorService;
		this.personNodeUidValidatorService = personNodeUidValidatorService;
		this.universityNodeUidGeneratorService = universityNodeUidGeneratorService;
		this.universityNodeUidValidatorService = universityNodeUidValidatorService;
	}

	@Override
	public Address validateEntityRelationships(Address address) {
		if(!address.getPersons().isEmpty()) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", address);
			}
			Set<Person> validatedPersons = new HashSet<>();
			for(Person person : address.getPersons()) {
				person.setNodeUid(personNodeUidGeneratorService.generateNodeUid(person));
				validatedPersons.add(personNodeUidValidatorService.validateNodeUid(person));
			}
			address.setPersons(validatedPersons);
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", address);
			}
		}
		
		if(address.getUniversity() != null) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", address);
			}
			address.getUniversity().setNodeUid(universityNodeUidGeneratorService.generateNodeUid(address.getUniversity()));
			address.setUniversity(universityNodeUidValidatorService.validateNodeUid(address.getUniversity()));
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", address);
			}
		}
		
		return address;
	}

}
