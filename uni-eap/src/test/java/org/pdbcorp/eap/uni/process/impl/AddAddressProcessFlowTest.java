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
package org.pdbcorp.eap.uni.process.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.process.flow.AddEntityProcessFlow;
import org.pdbcorp.eap.uni.service.generate.GenerateNodeUidService;
import org.pdbcorp.eap.uni.service.retrieve.RetrieveEntityDetailsService;
import org.pdbcorp.eap.uni.service.validate.ValidateEntityRelationshipsService;
import org.pdbcorp.eap.uni.service.validate.ValidateNodeUidService;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class AddAddressProcessFlowTest {

	@Mock
	private RetrieveEntityDetailsService<Address> retrieveEntityDetailsService;
	@Mock
	private GenerateNodeUidService<Address> generateNodeUidService;
	@Mock
	private ValidateEntityRelationshipsService<Address> validateEntityRelationshipsService;
	@Mock
	private ValidateNodeUidService<Address> validateNodeUidService;

	@InjectMocks
	private AddEntityProcessFlow<Address> addEntityProcessFlow = new AddAddressProcessFlow(
			retrieveEntityDetailsService, generateNodeUidService, validateEntityRelationshipsService, validateNodeUidService);

	@DisplayName("")
	@Test
	void validExecuteTest() throws Exception {
		Address address = TestDataFactoryUtil.generateAddressProvinceInstance();
		when(generateNodeUidService.generateNodeUid(address)).thenReturn("ABCD1234");
		when(validateNodeUidService.validateNodeUid(address)).thenReturn(address);
		when(validateEntityRelationshipsService.validateEntityRelationships(address)).thenReturn(address);
		when(retrieveEntityDetailsService.saveEntity(address)).thenReturn(address);
		assertNotNull(addEntityProcessFlow.execute(address));
	}

}
