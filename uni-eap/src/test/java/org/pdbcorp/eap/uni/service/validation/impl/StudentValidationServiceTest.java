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
package org.pdbcorp.eap.uni.service.validation.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Student;
import org.pdbcorp.eap.uni.data.repo.BaseEntityRepository;
import org.pdbcorp.eap.uni.service.generation.EntityNodeUidGenerationService;
import org.pdbcorp.eap.uni.service.validation.EntityValidationService;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
class StudentValidationServiceTest {

	@Mock
	private BaseEntityRepository<Student> repository;
	@Mock
	private EntityNodeUidGenerationService<Student> generator;

	@InjectMocks
	private EntityValidationService<Student> service = new StudentValidationService(repository, generator, log);

	@DisplayName("Successfully query STUDENT nodes and identify node already exists")
	@Test
	void validValidateExistsTrueState() throws Exception {
		Student dbNode = TestDataFactoryUtil.generateStudentInstanceWithPerson();
		dbNode.setNodeUid("ABCD1234");
		when(generator.generateNodeUid(dbNode)).thenReturn(dbNode.getNodeUid());
		when(repository.findByNodeUid(anyString())).thenReturn(Optional.of(dbNode));
		Student actual = service.validateNodeUid(dbNode);
		assertEquals(dbNode, actual);
	}

	@DisplayName("Successfully query STUDENT nodes and identify node is unique")
	@Test
	void validValidateExistsFalse() throws Exception {
		Student dbNode1 = TestDataFactoryUtil.generateStudentInstanceWithPerson();
		Student dbNode2 = TestDataFactoryUtil.generateStudentInstanceWithPerson();
		dbNode1.setNodeUid("EFGH4567");
		dbNode2.setName("124 Fake St");
		when(generator.generateNodeUid(dbNode2)).thenReturn("ABCD1234");
		when(repository.findByNodeUid(anyString())).thenReturn(Optional.empty());
		Student actual = service.validateNodeUid(dbNode2);
		assertEquals(dbNode2, actual);
		assertNotEquals(dbNode1, actual);
	}

}
