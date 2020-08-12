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

import org.pdbcorp.eap.uni.data.model.Course;
import org.pdbcorp.eap.uni.data.model.Department;
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
class SubjectRelationshipsValidatorService implements ValidateEntityRelationshipsService<Subject> {

	private ValidateNodeUidService<Course> courseNodeUidValidatorService;
	private ValidateNodeUidService<Department> departmentNodeUidValidatorService;
	private ValidateNodeUidService<Teacher> teacherNodeUidValidatorService;

	@Autowired
	SubjectRelationshipsValidatorService(
			@Qualifier("courseNodeUidValidatorService") ValidateNodeUidService<Course> courseNodeUidValidatorService,
			@Qualifier("departmentNodeUidValidatorService") ValidateNodeUidService<Department> departmentNodeUidValidatorService,
			@Qualifier("teacherNodeUidValidatorService") ValidateNodeUidService<Teacher> teacherNodeUidValidatorService) {
		
		this.courseNodeUidValidatorService = courseNodeUidValidatorService;
		this.departmentNodeUidValidatorService = departmentNodeUidValidatorService;
		this.teacherNodeUidValidatorService = teacherNodeUidValidatorService;
	}

	@Override
	public Subject validateEntityRelationships(Subject subject) {
		if(!subject.getCourses().isEmpty()) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", subject);
			}
			Set<Course> validatedCourses = new HashSet<>();
			for(Course course : subject.getCourses()) {
				validatedCourses.add(courseNodeUidValidatorService.validateNodeUid(course));
			}
			subject.setCourses(validatedCourses);
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", subject);
			}
		}
		
		if(!subject.getTeachers().isEmpty()) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", subject);
			}
			Set<Teacher> validatedTeachers = new HashSet<>();
			for(Teacher teacher : subject.getTeachers()) {
				validatedTeachers.add(teacherNodeUidValidatorService.validateNodeUid(teacher));
			}
			subject.setTeachers(validatedTeachers);
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", subject);
			}
		}
		
		if(log.isTraceEnabled()) {
			log.trace("Updating entity: {}", subject);
		}
		subject.setDepartment(departmentNodeUidValidatorService.validateNodeUid(subject.getDepartment()));
		if(log.isDebugEnabled()) {
			log.debug("Updated entity: {}", subject);
		}
		
		return subject;
	}

}
