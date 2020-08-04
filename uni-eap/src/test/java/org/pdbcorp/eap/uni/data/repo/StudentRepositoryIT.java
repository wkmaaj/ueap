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
package org.pdbcorp.eap.uni.data.repo;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pdbcorp.eap.uni.data.model.Enrollment;
import org.pdbcorp.eap.uni.data.model.Student;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@ActiveProfiles("test")
@DataNeo4jTest
@DirtiesContext
@ExtendWith(SpringExtension.class)
@Slf4j
class StudentRepositoryIT {

	@Autowired
	private StudentRepository repo;

	@DisplayName("Successfully save a STUDENT node")
	@Test
	void validSaveTest() throws Exception {
		Student entity = TestDataFactoryUtil.generateStudentInstance();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
	}

	@DisplayName("Successfully save a STUDENT node with a ENROLLMENT related COURSE node")
	@Test
	void validSaveTestWithEnrollment() throws Exception {
		Student entity = TestDataFactoryUtil.generateStudentInstanceWithPerson();
		Set<Enrollment> enrollments = new HashSet<>();
		enrollments.add(TestDataFactoryUtil.generateEnrollmentInstance(TestDataFactoryUtil.generateCourseInstance(), entity));
		entity.setEnrollments(enrollments);
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
	}

	@DisplayName("Successfully save a STUDENT node with a related PERSON node")
	@Test
	void validSaveTestWithPerson() throws Exception {
		Student entity = TestDataFactoryUtil.generateStudentInstanceWithPerson();
		entity = repo.save(entity);
		log.debug("{}", entity.getPerson());
		assertFalse(StringUtils.isBlank(entity.getId()));
	}

}
