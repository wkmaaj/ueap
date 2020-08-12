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

import org.pdbcorp.eap.uni.data.model.Course;
import org.pdbcorp.eap.uni.data.model.Subject;
import org.pdbcorp.eap.uni.data.model.Teacher;
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
class CourseRelationshipsValidatorService implements ValidateEntityRelationshipsService<Course> {

	private ValidateNodeUidService<Subject> subjectNodeUidValidatorService;
	private ValidateNodeUidService<Teacher> teacherNodeUidValidatorService;

	@Autowired
	CourseRelationshipsValidatorService(
			@Qualifier("subjectNodeUidValidatorService") ValidateNodeUidService<Subject> subjectNodeUidValidatorService,
			@Qualifier("teacherNodeUidValidatorService") ValidateNodeUidService<Teacher> teacherNodeUidValidatorService) {
		
		this.subjectNodeUidValidatorService = subjectNodeUidValidatorService;
		this.teacherNodeUidValidatorService = teacherNodeUidValidatorService;
	}

	@Override
	public Course validateEntityRelationships(Course course) {
		if(course.getSubject() != null) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", course);
			}
			course.setSubject(subjectNodeUidValidatorService.validateNodeUid(course.getSubject()));
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", course);
			}
		}
		
		if(course.getTeacher() != null) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", course);
			}
			course.setTeacher(teacherNodeUidValidatorService.validateNodeUid(course.getTeacher()));
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", course);
			}
		}
		
		return course;
	}
	
}
