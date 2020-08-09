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
package org.pdbcorp.eap.uni.service.persist.impl;

import java.util.HashSet;
import java.util.Set;

import org.pdbcorp.eap.uni.data.model.Department;
import org.pdbcorp.eap.uni.data.model.Subject;
import org.pdbcorp.eap.uni.data.model.University;
import org.pdbcorp.eap.uni.service.details.EntityDetailsService;
import org.pdbcorp.eap.uni.service.persist.EntityPersistenceService;
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
public class DepartmentPersistenceService implements EntityPersistenceService<Department> {

	private EntityDetailsService<Subject> subjectDetailsService;
	private EntityDetailsService<University> universityDetailsService;

	@Autowired
	public DepartmentPersistenceService(
			@Qualifier("subjectDetailsService") EntityDetailsService<Subject> subjectDetailsService,
			@Qualifier("universityDetailsService") EntityDetailsService<University> universityDetailsService) {
		
		this.subjectDetailsService = subjectDetailsService;
		this.universityDetailsService = universityDetailsService;
	}

	@Override
	public Department validateEntityRelationships(Department department) {
		if(!department.getSubjects().isEmpty()) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", department);
			}
			Set<Subject> validatedSubjects = new HashSet<>();
			for(Subject subject : department.getSubjects()) {
				validatedSubjects.add(subjectDetailsService.validateAndSave(subject));
			}
			department.setSubjects(validatedSubjects);
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", department);
			}
		}
		
		if(log.isTraceEnabled()) {
			log.trace("Updating entity: {}", department);
		}
		department.setUniversity(universityDetailsService.validateAndSave(department.getUniversity()));
		if(log.isDebugEnabled()) {
			log.debug("Updated entity: {}", department);
		}
		
		return department;
	}

}
