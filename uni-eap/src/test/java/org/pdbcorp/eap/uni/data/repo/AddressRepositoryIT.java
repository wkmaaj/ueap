/*
\ * Copyright 2020 PDB Corp.
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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

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
class AddressRepositoryIT {

	@Autowired
	private AddressRepository repo;

	@DisplayName("Successfully save an ADDRESS node")
	@Test
	void validSaveTest() throws Exception {
		Address entity = TestDataFactoryUtil.generateAddressStateInstance();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
	}

	@DisplayName("Successfully save an ADDRESS node with a related PERSON node")
	@Test
	void validSaveTestWithPerson() throws Exception {
		Address entity = TestDataFactoryUtil.generateAddressInstanceWithPerson();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		for(Person person : entity.getPersons()) {
			assertFalse(StringUtils.isBlank(person.getId()));
		}
	}

	@DisplayName("Successfully save an ADDRESS node with a related UNIVERSITY node")
	@Test
	void validSaveTestWithUniversity() throws Exception {
		Address entity = TestDataFactoryUtil.generateAddressInstanceWithUniversity();
		entity = repo.save(entity);
		log.debug("{}", entity);
		assertFalse(StringUtils.isBlank(entity.getId()));
		assertFalse(StringUtils.isBlank(entity.getUniversity().getId()));
	}

	@DisplayName("Successfully lookup an ADDRESS node by ADDR_LINE_1 field property")
	@Test
	void validFindByAddrLine1Test() throws Exception {
		Address expected = repo.save(TestDataFactoryUtil.generateAddressStateInstance());
		Collection<Address> result = repo.findByAddrLine1("123 Fake St");
		assertTrue(result.contains(expected));
	}

}
