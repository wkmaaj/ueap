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

import org.pdbcorp.eap.uni.data.model.Subject;
import org.pdbcorp.eap.uni.data.repo.SubjectRepository;
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
public class SubjectDetailsService extends BaseEntityDetailsService<Subject> {

	private SubjectRepository subjectRepository;
	private EntityPersistenceService<Subject> subjectPersistenceService;

	@Autowired
	public SubjectDetailsService(SubjectRepository subjectRepository,
			@Qualifier("subjectValidationService") EntityValidationService<Subject> subjectValidationService,
			@Qualifier("subjectPersistenceService") EntityPersistenceService<Subject> subjectPersistenceService) {
		
		super(subjectRepository, subjectValidationService);
		this.subjectRepository = subjectRepository;
		this.subjectPersistenceService = subjectPersistenceService;
	}

	@Override
	public Subject validateAndSave(Subject subject) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", subject);
		}
		subject = super.validateAndSave(subject);
		subject = subjectPersistenceService.validateEntityRelationships(subject);
		subject = subjectRepository.save(subject);
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", subject);
		}
		return subject;
	}

	public Collection<Subject> findByName(String name) {
		return subjectRepository.findByName(name);
	}

}
