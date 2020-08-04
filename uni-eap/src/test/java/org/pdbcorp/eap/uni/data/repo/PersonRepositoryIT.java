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

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.model.Person;
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
class PersonRepositoryIT {

	@Autowired
	private PersonRepository repo;

	@DisplayName("Successfully save a PERSON node")
	@Test
	void validSaveTest() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstance();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
	}

	@DisplayName("Successfully save a PERSON node with a related ADDRESS node")
	@Test
	void validSaveTestWithAddress() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddress();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		for(Address address : entity.getAddress()) {
			assertFalse(StringUtils.isBlank(address.getId()));
		}
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS and STUDENT nodes")
	@Test
	void validSaveTestWithAddressStudent() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressStudent();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getOccupation().getId()));
		for(Address address : entity.getAddress()) {
			assertFalse(StringUtils.isBlank(address.getId()));
		}
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS STUDENT and VEHICLE nodes")
	@Test
	void validSaveTestWithAddressStudentVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressStudentVehicle();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getOccupation().getId()));
		assertFalse(StringUtils.isBlank(entity.getVehicle().getId()));
		for(Address address : entity.getAddress()) {
			assertFalse(StringUtils.isBlank(address.getId()));
		}
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS and TEACHER nodes")
	@Test
	void validSaveTestWithAddressTeacher() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressTeacher();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getOccupation().getId()));
		for(Address address : entity.getAddress()) {
			assertFalse(StringUtils.isBlank(address.getId()));
		}
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS TEACHER and VEHICLE nodes")
	@Test
	void validSaveTestWithAddressTeacherVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressTeacherVehicle();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getOccupation().getId()));
		assertFalse(StringUtils.isBlank(entity.getVehicle().getId()));
		for(Address address : entity.getAddress()) {
			assertFalse(StringUtils.isBlank(address.getId()));
		}
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS and VEHICLE nodes")
	@Test
	void validSaveTestWithAddressVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressVehicle();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getVehicle().getId()));
		for(Address address : entity.getAddress()) {
			assertFalse(StringUtils.isBlank(address.getId()));
		}
	}

	@DisplayName("Successfully save a PERSON node with a related STUDENT node")
	@Test
	void validSaveTestWithStudent() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithStudent();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getOccupation().getId()));
	}

	@DisplayName("Successfully save a PERSON node with related STUDENT and VEHICLE nodes")
	@Test
	void validSaveTestWithStudentVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithStudentVehicle();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getOccupation().getId()));
		assertFalse(StringUtils.isBlank(entity.getVehicle().getId()));
	}

	@DisplayName("Successfully save a PERSON node with a related TEACHER node")
	@Test
	void validSaveTestWithTeacher() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithTeacher();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getOccupation().getId()));
	}

	@DisplayName("Successfully save a PERSON node with related TEACHER and VEHICLE nodes")
	@Test
	void validSaveTestWithTeacherVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithTeacherVehicle();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getOccupation().getId()));
		assertFalse(StringUtils.isBlank(entity.getVehicle().getId()));
	}

	@DisplayName("Successfully save a PERSON node with a related VEHICLE node")
	@Test
	void validSaveTestWithVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithVehicle();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getVehicle().getId()));
	}

}
