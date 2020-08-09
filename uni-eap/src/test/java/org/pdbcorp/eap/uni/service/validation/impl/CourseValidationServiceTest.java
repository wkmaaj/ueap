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
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Course;
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
class CourseValidationServiceTest {

	@Mock
	private BaseEntityRepository<Course> repository;
	@Mock
	private EntityNodeUidGenerationService<Course> generator;

	@InjectMocks
	private EntityValidationService<Course> service = new CourseValidationService(repository, generator, log);

	@DisplayName("Successfully query COURSE nodes and identify node already exists")
	@Test
	void validValidateExistsTrueState() throws Exception {
		Course dbNode = TestDataFactoryUtil.generateCourseInstance();
		dbNode.setNodeUid("ABCD1234");
		when(generator.generateNodeUid(dbNode)).thenReturn(dbNode.getNodeUid());
		Course actual = service.validateNodeUid(dbNode);
		assertEquals(dbNode, actual);
	}

	@DisplayName("Successfully query COURSE nodes and identify node is unique")
	@Test
	void validValidateExistsFalse() throws Exception {
		Course dbNode1 = TestDataFactoryUtil.generateCourseInstance();
		Course dbNode2 = TestDataFactoryUtil.generateCourseInstance();
		dbNode1.setNodeUid("EFGH4567");
		dbNode2.setName("DEV Course");
		when(generator.generateNodeUid(dbNode2)).thenReturn("ABCD1234");
		Collection<Course> dbNodes = new ArrayList<>();
		dbNodes.add(dbNode1);
		Course actual = service.validateNodeUid(dbNode2);
		assertEquals(dbNode2, actual);
		assertNotEquals(dbNode1, actual);
	}

}
