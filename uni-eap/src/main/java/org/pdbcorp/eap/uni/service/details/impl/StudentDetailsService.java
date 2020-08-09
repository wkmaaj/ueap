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

import org.pdbcorp.eap.uni.data.model.Student;
import org.pdbcorp.eap.uni.data.repo.StudentRepository;
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
public class StudentDetailsService extends BaseEntityDetailsService<Student> {

	private StudentRepository studentRepository;
	private EntityPersistenceService<Student> studentPersistenceService;

	@Autowired
	public StudentDetailsService(StudentRepository studentRepository,
			@Qualifier("studentValidationService") EntityValidationService<Student> studentValidationService,
			@Qualifier("studentPersistenceService") EntityPersistenceService<Student> studentPersistenceService) {
		
		super(studentRepository, studentValidationService);
		this.studentRepository = studentRepository;
		this.studentPersistenceService = studentPersistenceService;
	}

	@Override
	public Student validateAndSave(Student student) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", student);
		}
		student = super.validateAndSave(student);
		student = studentPersistenceService.validateEntityRelationships(student);
		student = studentRepository.save(student);
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", student);
		}
		return student;
	}

	public Collection<Student> findByName(String name) {
		return studentRepository.findByName(name);
	}

}
