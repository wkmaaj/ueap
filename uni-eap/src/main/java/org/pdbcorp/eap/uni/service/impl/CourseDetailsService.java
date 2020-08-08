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
package org.pdbcorp.eap.uni.service.impl;

import java.util.Collection;

import org.pdbcorp.eap.uni.data.model.Course;
import org.pdbcorp.eap.uni.data.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	public CourseDetailsService(CourseRepository courseRepository) {
		super(courseRepository);
		this.courseRepository = courseRepository;
	}

	public Collection<Course> findByName(String name) {
		return courseRepository.findByName(name);
	}

	public Course saveCourse(Course course) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", course);
		}
		course = courseRepository.save(course);
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", course);
		}
		return course;
	}

}
