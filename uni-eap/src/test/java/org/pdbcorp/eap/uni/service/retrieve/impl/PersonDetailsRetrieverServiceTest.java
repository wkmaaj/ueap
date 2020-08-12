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
package org.pdbcorp.eap.uni.service.retrieve.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.data.repo.PersonRepository;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class PersonDetailsRetrieverServiceTest {

	@Mock
	private PersonRepository repository;

	@InjectMocks
	private PersonDetailsRetrieverService service = new PersonDetailsRetrieverService(repository);

	@DisplayName("Successfully query PERSON nodes using FIRST_NAME")
	@Test
	void validFindByFnameTest() throws Exception {
		Person expected = TestDataFactoryUtil.generatePersonInstance();
		when(repository.findByFname(expected.getFname())).thenReturn(Arrays.asList(expected));
		Collection<Person> result = service.findByFname(expected.getFname());
		assertTrue(result.contains(expected));
	}

	@DisplayName("Successfully save a PERSON node via the details service")
	@Test
	void validSaveEntityTest() throws Exception {
		Person expected = TestDataFactoryUtil.generatePersonInstance();
		when(repository.save(any())).thenReturn(expected);
		assertEquals(expected, service.saveEntity(expected));
	}

}
