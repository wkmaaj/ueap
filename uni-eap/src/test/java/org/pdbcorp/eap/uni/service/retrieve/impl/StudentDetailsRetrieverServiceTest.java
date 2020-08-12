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
import org.pdbcorp.eap.uni.data.model.Student;
import org.pdbcorp.eap.uni.data.repo.StudentRepository;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class StudentDetailsRetrieverServiceTest {

	@Mock
	private StudentRepository repository;

	@InjectMocks
	private StudentDetailsRetrieverService service = new StudentDetailsRetrieverService(repository);

	@DisplayName("Successfully query STUDENT nodes using NAME")
	@Test
	void validFindByFnameTest() throws Exception {
		Student expected = TestDataFactoryUtil.generateStudentInstanceWithPerson();
		when(repository.findByName(expected.getName())).thenReturn(Arrays.asList(expected));
		Collection<Student> result = service.findByName(expected.getName());
		assertTrue(result.contains(expected));
	}

	@DisplayName("Successfully save a STUDENT node via the details service")
	@Test
	void validSaveEntityTest() throws Exception {
		Student expected = TestDataFactoryUtil.generateStudentInstanceWithPerson();
		when(repository.save(any())).thenReturn(expected);
		assertEquals(expected, service.saveEntity(expected));
	}

}
