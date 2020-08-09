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

import org.pdbcorp.eap.uni.data.model.Course;
import org.pdbcorp.eap.uni.data.repo.CourseRepository;
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
public class CourseDetailsService extends BaseEntityDetailsService<Course> {

	private CourseRepository courseRepository;
	private EntityPersistenceService<Course> coursePersistenceService;

	@Autowired
	public CourseDetailsService(CourseRepository courseRepository,
			@Qualifier("courseValidationService") EntityValidationService<Course> courseValidationService,
			@Qualifier("coursePersistenceService") EntityPersistenceService<Course> coursePersistenceService) {
		
		super(courseRepository, courseValidationService);
		this.courseRepository = courseRepository;
		this.coursePersistenceService = coursePersistenceService;
	}

	@Override
	public Course validateAndSave(Course course) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", course);
		}
		course = super.validateAndSave(course);
		course = coursePersistenceService.validateEntityRelationships(course);
		course = courseRepository.save(course);
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", course);
		}
		return course;
	}

	public Collection<Course> findByName(String name) {
		return courseRepository.findByName(name);
	}

}
