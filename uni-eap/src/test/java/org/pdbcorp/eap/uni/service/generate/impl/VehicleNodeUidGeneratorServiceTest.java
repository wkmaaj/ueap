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
package org.pdbcorp.eap.uni.service.generate.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Vehicle;
import org.pdbcorp.eap.uni.service.generate.GenerateNodeUidService;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class VehicleNodeUidGeneratorServiceTest {

	private GenerateNodeUidService<Vehicle> service = new VehicleNodeUidGeneratorService();

	@DisplayName("Successfully generate a NODE_UID string for a VEHICLE entity")
	@Test
	void validGenerateNodeUidAddressState() throws Exception {
		Vehicle entity = TestDataFactoryUtil.generateVehicleInstanceWithPerson();
		String nodeUid = service.generateNodeUid(entity);
		assertFalse(StringUtils.isBlank(nodeUid));
		assertTrue(nodeUid.contains(entity.getMake()));
		assertTrue(nodeUid.contains(entity.getModel()));
		assertTrue(nodeUid.contains(String.valueOf(entity.getYear())));
		assertTrue(nodeUid.contains(entity.getLicensePlate()));
		assertTrue(nodeUid.contains(entity.getVin()));
	}

}
