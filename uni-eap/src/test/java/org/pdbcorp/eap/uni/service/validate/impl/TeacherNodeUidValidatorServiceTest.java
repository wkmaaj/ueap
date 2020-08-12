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
package org.pdbcorp.eap.uni.service.validate.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Teacher;
import org.pdbcorp.eap.uni.data.repo.BaseEntityRepository;
import org.pdbcorp.eap.uni.service.validate.ValidateNodeUidService;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class TeacherNodeUidValidatorServiceTest {

	@Mock
	private BaseEntityRepository<Teacher> repository;

	@InjectMocks
	private ValidateNodeUidService<Teacher> service = new TeacherNodeUidValidatorService(repository);

	@DisplayName("Successfully query TEACHER nodes and identify node already exists")
	@Test
	void validValidateExistsTrueState() throws Exception {
		Teacher dbNode = TestDataFactoryUtil.generateTeacherInstanceWithPerson();
		dbNode.setNodeUid("ABCD1234");
		when(repository.findByNodeUid(dbNode.getNodeUid())).thenReturn(Optional.of(dbNode));
		Teacher actual = service.validateNodeUid(dbNode);
		assertEquals(dbNode, actual);
	}

	@DisplayName("Successfully query TEACHER nodes and identify node is unique")
	@Test
	void validValidateExistsFalse() throws Exception {
		Teacher dbNode1 = TestDataFactoryUtil.generateTeacherInstanceWithPerson();
		Teacher dbNode2 = TestDataFactoryUtil.generateTeacherInstanceWithPerson();
		dbNode1.setNodeUid("EFGH4567");
		dbNode2.setNodeUid("ABCD1234");
		dbNode2.setName("124 Fake St");
		when(repository.findByNodeUid(dbNode2.getNodeUid())).thenReturn(Optional.empty());
		Teacher actual = service.validateNodeUid(dbNode2);
		assertEquals(dbNode2, actual);
		assertNotEquals(dbNode1, actual);
	}

}
