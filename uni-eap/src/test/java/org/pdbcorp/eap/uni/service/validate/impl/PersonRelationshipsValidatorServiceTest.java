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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.data.model.Vehicle;
import org.pdbcorp.eap.uni.service.generate.GenerateNodeUidService;
import org.pdbcorp.eap.uni.service.validate.ValidateEntityRelationshipsService;
import org.pdbcorp.eap.uni.service.validate.ValidateNodeUidService;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class PersonRelationshipsValidatorServiceTest {

	@Mock
	private GenerateNodeUidService<Address> addressNodeUidGeneratorService;
	@Mock
	private ValidateNodeUidService<Address> addressNodeUidValidatorService;
	@Mock
	private GenerateNodeUidService<Vehicle> vehicleNodeUidGeneratorService;
	@Mock
	private ValidateNodeUidService<Vehicle> vehicleNodeUidValidatorService;

	@InjectMocks
	private ValidateEntityRelationshipsService<Person> persistence = new PersonRelationshipsValidatorService(
			addressNodeUidGeneratorService, addressNodeUidValidatorService,
			vehicleNodeUidGeneratorService, vehicleNodeUidValidatorService);

	@DisplayName("")
	@Test
	void validValidateEntityRelationshipsTest() throws Exception {
		Person expected = TestDataFactoryUtil.generatePersonInstanceWithAddressStudentVehicle();
		when(addressNodeUidValidatorService.validateNodeUid(any())).thenReturn(TestDataFactoryUtil.generateAddressStateInstance());
		when(vehicleNodeUidValidatorService.validateNodeUid(any())).thenReturn(expected.getVehicle());
		Person actual = persistence.validateEntityRelationships(expected);
		assertEquals(expected, actual);
	}

	@DisplayName("")
	@Test
	void emptyValidateEntityRelationshipsTest() throws Exception {
		Person person = persistence.validateEntityRelationships(TestDataFactoryUtil.generatePersonInstance());
		assertNotNull(person);
	}

}
