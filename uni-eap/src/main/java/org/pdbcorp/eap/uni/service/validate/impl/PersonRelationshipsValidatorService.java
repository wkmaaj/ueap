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
import org.pdbcorp.eap.uni.data.model.Vehicle;
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
class PersonRelationshipsValidatorService implements ValidateEntityRelationshipsService<Person> {

	private GenerateNodeUidService<Address> addressNodeUidGeneratorService;
	private ValidateNodeUidService<Address> addressNodeUidValidatorService;
	private GenerateNodeUidService<Vehicle> vehicleNodeUidGeneratorService;
	private ValidateNodeUidService<Vehicle> vehicleNodeUidValidatorService;

	@Autowired
	PersonRelationshipsValidatorService(
			@Qualifier("addressNodeUidGeneratorService") GenerateNodeUidService<Address> addressNodeUidGeneratorService,
			@Qualifier("addressNodeUidValidatorService") ValidateNodeUidService<Address> addressNodeUidValidatorService,
			@Qualifier("vehicleNodeUidGeneratorService") GenerateNodeUidService<Vehicle> vehicleNodeUidGeneratorService,
			@Qualifier("vehicleNodeUidValidatorService") ValidateNodeUidService<Vehicle> vehicleNodeUidValidatorService) {
		
		this.addressNodeUidGeneratorService = addressNodeUidGeneratorService;
		this.addressNodeUidValidatorService = addressNodeUidValidatorService;
		this.vehicleNodeUidGeneratorService = vehicleNodeUidGeneratorService;
		this.vehicleNodeUidValidatorService = vehicleNodeUidValidatorService;
	}

	@Override
	public Person validateEntityRelationships(Person person) {
		if(!person.getAddresses().isEmpty()) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", person);
			}
			Set<Address> validatedAddresses = new HashSet<>();
			for(Address address : person.getAddresses()) {
				address.setNodeUid(addressNodeUidGeneratorService.generateNodeUid(address));
				validatedAddresses.add(addressNodeUidValidatorService.validateNodeUid(address));
			}
			person.setAddresses(validatedAddresses);
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", person);
			}
		}
		
		if(person.getVehicle() != null) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", person);
			}
			person.getVehicle().setNodeUid(vehicleNodeUidGeneratorService.generateNodeUid(person.getVehicle()));
			person.setVehicle(vehicleNodeUidValidatorService.validateNodeUid(person.getVehicle()));
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", person);
			}
		}
		
		return person;
	}

}
