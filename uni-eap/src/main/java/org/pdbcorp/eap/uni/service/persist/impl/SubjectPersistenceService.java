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

import org.pdbcorp.eap.uni.data.model.Course;
import org.pdbcorp.eap.uni.data.model.Department;
import org.pdbcorp.eap.uni.data.model.Subject;
import org.pdbcorp.eap.uni.data.model.Teacher;
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
public class SubjectPersistenceService implements EntityPersistenceService<Subject> {

	private EntityDetailsService<Course> courseDetailsService;
	private EntityDetailsService<Department> departmentDetailsService;
	private EntityDetailsService<Teacher> teacherDetailsService;

	@Autowired
	public SubjectPersistenceService(
			@Qualifier("courseDetailsService") EntityDetailsService<Course> courseDetailsService,
			@Qualifier("departmentDetailsService") EntityDetailsService<Department> departmentDetailsService,
			@Qualifier("teacherDetailsService") EntityDetailsService<Teacher> teacherDetailsService) {
		
		this.courseDetailsService = courseDetailsService;
		this.departmentDetailsService = departmentDetailsService;
		this.teacherDetailsService = teacherDetailsService;
	}

	@Override
	public Subject validateEntityRelationships(Subject subject) {
		if(!subject.getCourses().isEmpty()) {
			if(log.isTraceEnabled()) {
				log.trace("Updating entity: {}", subject);
			}
			Set<Course> validatedCourses = new HashSet<>();
			for(Course course : subject.getCourses()) {
				validatedCourses.add(courseDetailsService.validateAndSave(course));
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
				validatedTeachers.add(teacherDetailsService.validateAndSave(teacher));
			}
			subject.setTeachers(validatedTeachers);
			if(log.isDebugEnabled()) {
				log.debug("Updated entity: {}", subject);
			}
		}
		
		if(log.isTraceEnabled()) {
			log.trace("Updating entity: {}", subject);
		}
		subject.setDepartment(departmentDetailsService.validateAndSave(subject.getDepartment()));
		if(log.isDebugEnabled()) {
			log.debug("Updated entity: {}", subject);
		}
		
		return subject;
	}

}
