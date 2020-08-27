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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Department;
import org.pdbcorp.eap.uni.data.model.Subject;
import org.pdbcorp.eap.uni.data.model.University;
import org.pdbcorp.eap.uni.service.generate.GenerateNodeUidService;
import org.pdbcorp.eap.uni.service.validate.ValidateEntityRelationshipsService;
import org.pdbcorp.eap.uni.service.validate.ValidateNodeUidService;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class DepartmentRelationshipsValidatorServiceTest {

	@Mock
	private GenerateNodeUidService<Subject> subjectNodeUidGeneratorService;
	@Mock
	private ValidateNodeUidService<Subject> subjectNodeUidValidatorService;
	@Mock
	private GenerateNodeUidService<University> universityNodeUidGeneratorService;
	@Mock
	private ValidateNodeUidService<University> universityNodeUidValidatorService;

	@InjectMocks
	private ValidateEntityRelationshipsService<Department> persistence = new DepartmentRelationshipsValidatorService(
			subjectNodeUidGeneratorService, subjectNodeUidValidatorService,
			universityNodeUidGeneratorService, universityNodeUidValidatorService);

	@DisplayName("")
	@Test
	void validSubjectValidateEntityRelationshipsTest() throws Exception {
		Department expected = TestDataFactoryUtil.generateDepartmentInstanceWithUniversity();
		expected.getSubjects().add(TestDataFactoryUtil.generateSubjectInstance());
		when(subjectNodeUidValidatorService.validateNodeUid(any())).thenReturn(TestDataFactoryUtil.generateSubjectInstance());
		when(universityNodeUidValidatorService.validateNodeUid(any())).thenReturn(TestDataFactoryUtil.generateUniversityInstance());
		Department actual = persistence.validateEntityRelationships(expected);
		assertEquals(expected, actual);
	}

	@DisplayName("")
	@Test
	void validUniversityValidateEntityRelationshipsTest() throws Exception {
		Department expected = TestDataFactoryUtil.generateDepartmentInstanceWithUniversity();
		when(universityNodeUidValidatorService.validateNodeUid(any())).thenReturn(TestDataFactoryUtil.generateUniversityInstance());
		Department actual = persistence.validateEntityRelationships(expected);
		assertEquals(expected, actual);
	}

}
