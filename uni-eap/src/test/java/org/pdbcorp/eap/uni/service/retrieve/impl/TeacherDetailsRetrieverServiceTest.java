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
import org.pdbcorp.eap.uni.data.model.Teacher;
import org.pdbcorp.eap.uni.data.repo.TeacherRepository;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

import reactor.core.publisher.Mono;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class TeacherDetailsRetrieverServiceTest {

	@Mock
	private TeacherRepository repository;
	@Mock
	private Mono<Teacher> mockMono;

	@InjectMocks
	private TeacherDetailsRetrieverService service = new TeacherDetailsRetrieverService(repository);

	@DisplayName("Successfully query TEACHER nodes using NAME")
	@Test
	void validFindByNameTest() throws Exception {
		Teacher expected = TestDataFactoryUtil.generateTeacherInstanceWithPerson();
		when(repository.findByName(expected.getName())).thenReturn(Arrays.asList(expected));
		Collection<Teacher> result = service.findByName(expected.getName());
		assertTrue(result.contains(expected));
	}

	@DisplayName("Successfully save a TEACHER node via the details service")
	@Test
	void validSaveEntityTest() throws Exception {
		Teacher expected = TestDataFactoryUtil.generateTeacherInstanceWithPerson();
		when(repository.save(any())).thenReturn(mockMono);
		when(mockMono.block()).thenReturn(expected);
		assertEquals(expected, service.saveEntity(expected));
	}

}
