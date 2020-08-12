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
import org.pdbcorp.eap.uni.data.model.Department;
import org.pdbcorp.eap.uni.data.model.University;
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
class UniversityRelationshipsValidatorService implements ValidateEntityRelationshipsService<University> {

	private ValidateNodeUidService<Address> addressNodeUidValidatorService;
	private ValidateNodeUidService<Department> departmentNodeUidValidatorService;

	@Autowired
	UniversityRelationshipsValidatorService(
			@Qualifier("addressNodeUidValidatorService") ValidateNodeUidService<Address> addressNodeUidValidatorService,
			@Qualifier("departmentNodeUidValidatorService") ValidateNodeUidService<Department> departmentNodeUidValidatorService) {
		
		this.addressNodeUidValidatorService = addressNodeUidValidatorService;
		this.departmentNodeUidValidatorService = departmentNodeUidValidatorService;
	}

	@Override
	public University validateEntityRelationships(University university) {
		if(!university.getDepartments().isEmpty()) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", university);
			}
			Set<Department> validatedDepartments = new HashSet<>();
			for(Department department : university.getDepartments()) {
				validatedDepartments.add(departmentNodeUidValidatorService.validateNodeUid(department));
			}
			university.setDepartments(validatedDepartments);
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", university);
			}
		}
		
		if(university.getAddress() != null) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", university);
			}
			university.setAddress(addressNodeUidValidatorService.validateNodeUid(university.getAddress()));
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", university);
			}
		}
		
		return university;
	}

}
