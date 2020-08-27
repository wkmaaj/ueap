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

import org.pdbcorp.eap.uni.data.model.Department;
import org.pdbcorp.eap.uni.data.model.Subject;
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
class DepartmentRelationshipsValidatorService implements ValidateEntityRelationshipsService<Department> {

	private GenerateNodeUidService<Subject> subjectNodeUidGeneratorService;
	private ValidateNodeUidService<Subject> subjectNodeUidValidatorService;
	private GenerateNodeUidService<University> universityNodeUidGeneratorService;
	private ValidateNodeUidService<University> universityNodeUidValidatorService;

	@Autowired
	DepartmentRelationshipsValidatorService(
			@Qualifier("subjectNodeUidGeneratorService") GenerateNodeUidService<Subject> subjectNodeUidGeneratorService,
			@Qualifier("subjectNodeUidValidatorService") ValidateNodeUidService<Subject> subjectNodeUidValidatorService,
			@Qualifier("universityNodeUidGeneratorService") GenerateNodeUidService<University> universityNodeUidGeneratorService,
			@Qualifier("universityNodeUidValidatorService") ValidateNodeUidService<University> universityNodeUidValidatorService) {
		
		this.subjectNodeUidGeneratorService = subjectNodeUidGeneratorService;
		this.subjectNodeUidValidatorService = subjectNodeUidValidatorService;
		this.universityNodeUidGeneratorService = universityNodeUidGeneratorService;
		this.universityNodeUidValidatorService = universityNodeUidValidatorService;
	}

	@Override
	public Department validateEntityRelationships(Department department) {
		if(!department.getSubjects().isEmpty()) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", department);
			}
			Set<Subject> validatedSubjects = new HashSet<>();
			for(Subject subject : department.getSubjects()) {
				subject.setNodeUid(subjectNodeUidGeneratorService.generateNodeUid(subject));
				validatedSubjects.add(subjectNodeUidValidatorService.validateNodeUid(subject));
			}
			department.setSubjects(validatedSubjects);
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", department);
			}
		}
		
		if(log.isTraceEnabled()) {
			log.trace("Updating entity: {}", department);
		}
		department.getUniversity().setNodeUid(universityNodeUidGeneratorService.generateNodeUid(department.getUniversity()));
		department.setUniversity(universityNodeUidValidatorService.validateNodeUid(department.getUniversity()));
		if(log.isDebugEnabled()) {
			log.debug("Updated entity: {}", department);
		}
		
		return department;
	}

}
