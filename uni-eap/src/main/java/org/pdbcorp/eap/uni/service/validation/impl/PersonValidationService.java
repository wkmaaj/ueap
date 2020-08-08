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
package org.pdbcorp.eap.uni.service.validation.impl;

import java.util.Collection;

import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.data.repo.PersonRepository;
import org.pdbcorp.eap.uni.service.generation.impl.PersonUniqueStringGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Slf4j
@Service
public class PersonValidationService {

	private PersonRepository personRepository;
	private PersonUniqueStringGenerationService personUniqueStringGenerationService;

	@Autowired
	public PersonValidationService(
		PersonRepository personRepository, PersonUniqueStringGenerationService personUniqueStringGenerationService) {
		
		this.personRepository = personRepository;
		this.personUniqueStringGenerationService = personUniqueStringGenerationService;
	}

	/**
	 * This method queries the db via the 
	 * {@link org.pdbcorp.eap.uni.data.repo.PersonRepository#findByFnameAndMnameAndLname(String, String, String) findByFnameAndMnameAndLname}
	 * query method for any existing nodes that contain the same <code>fname</code>, <code>mname</code>, and <code>lname</code>
	 * field properties.<br>
	 * If the query returns a collection, then the {@link org.pdbcorp.eap.uni.data.model.Person#getPropsUid() propsUid} 
	 * of the input parameter object and the returned nodes are compared to see if equivalent. If a returned node is found
	 * to have an equivalent {@link org.pdbcorp.eap.uni.data.model.Person#getPropsUid() propsUid}, then that node is returned
	 * to the method caller. Else, the input parameter object is passed back to the method caller with its
	 * {@link org.pdbcorp.eap.uni.data.model.Person#getPropsUid() propsUid} property set.
	 * 
	 * @param person - the object to check the db for.
	 * @return Person - either the initial object passed in as a parameter, or the PERSON node currently present in the db.
	 */
	public Person validateExists(Person person) {
		person.setPropsUid(personUniqueStringGenerationService.generateUniqueIdString(person));
		Collection<Person> dbNodes = personRepository
			.findByFnameAndMnameAndLname(person.getFname(), person.getMname(), person.getLname());
		if(!dbNodes.isEmpty()) {
			for(Person dbNode : dbNodes) {
				if(person.getPropsUid().equals(dbNode.getPropsUid())) {
					if(log.isDebugEnabled()) {
						log.debug("Identified existing node with matching propsUid: {}", dbNode);
					}
					return dbNode;
				}
			}
		}
		return person;
	}

}
