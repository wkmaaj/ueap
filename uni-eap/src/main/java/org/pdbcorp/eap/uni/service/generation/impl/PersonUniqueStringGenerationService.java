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
package org.pdbcorp.eap.uni.service.generation.impl;

import org.apache.commons.lang3.StringUtils;
import org.pdbcorp.eap.uni.data.model.Person;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Slf4j
@Service
public class PersonUniqueStringGenerationService {

	/**
	 * This method constructs a <i>unique identifying</i> {@link java.lang.String String} based on the properties
	 * of the <code>PERSON</code> node. This {@link java.lang.String String} will be used in comparing and determining
	 * if an <code>PERSON</code> node already exists in the datastore with all the same field properties (i.e.
	 * <code>fname</code>, <code>mname</code>, <code>lname</code>, <code>dob</code>, <code>gender</code>).
	 * This method does not factor in relational properties (i.e. ignores <code>addresses</code>, <code>vehicle</code>,
	 * and <code>occupation</code> properties).
	 * 
	 * @param person - the <code>PERSON</code> object to construct the unique ID for.
	 * @return String - the unique ID for the node based off of its properties.
	 */
	public String generateUniqueIdString(Person person) {
		if(log.isTraceEnabled()) {
			log.trace("Building propsUid for entity: {}", person);
		}
		StringBuilder stringBuilder = new StringBuilder();
		if(!StringUtils.isBlank(person.getFname())) {
			stringBuilder.append(person.getFname());
			if(!StringUtils.isBlank(person.getMname())) {
				stringBuilder.append(", ").append(person.getMname());
			}
			if(!StringUtils.isBlank(person.getLname())) {
				stringBuilder.append(", ").append(person.getLname());
			}
			if(person.getDob() != null) {
				stringBuilder.append(", ").append(person.getDob());
			}
			if(person.getGender() != null) {
				stringBuilder.append(", ").append(person.getGender().getName());
			}
		}
		if(log.isDebugEnabled()) {
			log.debug("Successfully constructed propsUid: {}", stringBuilder.toString());
		}
		return stringBuilder.toString();
	}

}
