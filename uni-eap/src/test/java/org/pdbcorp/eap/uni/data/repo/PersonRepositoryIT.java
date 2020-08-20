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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.springframework.boot.test.autoconfigure.data.ReactiveDataNeo4jTest;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@ActiveProfiles("test")
@DirtiesContext
@ExtendWith(SpringExtension.class)
@ReactiveDataNeo4jTest
@Slf4j
class PersonRepositoryIT extends BaseRepositoryIT {

	@Autowired
	private PersonRepository repo;

	@BeforeAll
	static void setUp() throws Exception {
		if(!neo4jContainer.isRunning())
			neo4jContainer.start();
	}

	@DisplayName("Successfully save a PERSON node")
	@Test
	void validSaveTest() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstance();
		repo.save(entity).subscribe(
				value -> assertFalse(StringUtils.isBlank(value.getId())),
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with a related ADDRESS node")
	@Test
	void validSaveTestWithAddress() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddress();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					for(Address address : value.getAddresses()) {
						assertFalse(StringUtils.isBlank(address.getId()));
					}
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS and STUDENT nodes")
	@Test
	void validSaveTestWithAddressStudent() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressStudent();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					assertFalse(StringUtils.isBlank(value.getOccupation().getId()));
					for(Address address : value.getAddresses()) {
						assertFalse(StringUtils.isBlank(address.getId()));
					}
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS STUDENT and VEHICLE nodes")
	@Test
	void validSaveTestWithAddressStudentVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressStudentVehicle();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					assertFalse(StringUtils.isBlank(value.getOccupation().getId()));
					assertFalse(StringUtils.isBlank(value.getVehicle().getId()));
					for(Address address : value.getAddresses()) {
						assertFalse(StringUtils.isBlank(address.getId()));
					}
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS and TEACHER nodes")
	@Test
	void validSaveTestWithAddressTeacher() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressTeacher();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					assertFalse(StringUtils.isBlank(value.getOccupation().getId()));
					for(Address address : value.getAddresses()) {
						assertFalse(StringUtils.isBlank(address.getId()));
					}
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS TEACHER and VEHICLE nodes")
	@Test
	void validSaveTestWithAddressTeacherVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressTeacherVehicle();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					assertFalse(StringUtils.isBlank(value.getOccupation().getId()));
					assertFalse(StringUtils.isBlank(value.getVehicle().getId()));
					for(Address address : value.getAddresses()) {
						assertFalse(StringUtils.isBlank(address.getId()));
					}
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with related ADDRESS and VEHICLE nodes")
	@Test
	void validSaveTestWithAddressVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithAddressVehicle();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					assertFalse(StringUtils.isBlank(value.getVehicle().getId()));
					for(Address address : value.getAddresses()) {
						assertFalse(StringUtils.isBlank(address.getId()));
					}
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with a related STUDENT node")
	@Test
	void validSaveTestWithStudent() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithStudent();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(entity.getId()));
					assertFalse(StringUtils.isBlank(entity.getOccupation().getId()));
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with related STUDENT and VEHICLE nodes")
	@Test
	void validSaveTestWithStudentVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithStudentVehicle();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					assertFalse(StringUtils.isBlank(value.getOccupation().getId()));
					assertFalse(StringUtils.isBlank(value.getVehicle().getId()));
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with a related TEACHER node")
	@Test
	void validSaveTestWithTeacher() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithTeacher();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					assertFalse(StringUtils.isBlank(value.getOccupation().getId()));
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with related TEACHER and VEHICLE nodes")
	@Test
	void validSaveTestWithTeacherVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithTeacherVehicle();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					assertFalse(StringUtils.isBlank(value.getOccupation().getId()));
					assertFalse(StringUtils.isBlank(value.getVehicle().getId()));
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save a PERSON node with a related VEHICLE node")
	@Test
	void validSaveTestWithVehicle() throws Exception {
		Person entity = TestDataFactoryUtil.generatePersonInstanceWithVehicle();
		repo.save(entity).subscribe(
				value -> {
					assertFalse(StringUtils.isBlank(value.getId()));
					assertFalse(StringUtils.isBlank(value.getVehicle().getId()));
				},
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

}
