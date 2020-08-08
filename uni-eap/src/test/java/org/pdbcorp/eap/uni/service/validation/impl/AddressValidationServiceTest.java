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

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.repo.AddressRepository;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class AddressValidationServiceTest {

	@Mock
	private AddressRepository repo;

	@InjectMocks
	private AddressValidationService service = new AddressValidationService(repo);

	@DisplayName("Successfully query ADDRESS nodes and identify STATE node already exists")
	@Test
	void validValidateExistsTrueState() throws Exception {
		Address dbNode1 = TestDataFactoryUtil.generateAddressStateInstance();
		Address dbNode2 = TestDataFactoryUtil.generateAddressStateInstance();
		dbNode2.setAddrLine1("124 Fake St");
		Collection<Address> addresses = new ArrayList<>();
		addresses.add(dbNode1);
		when(repo.findByAddrLine1AndCityAndCountry(anyString(), anyString(), anyString())).thenReturn(addresses);
		Address actual = service.validateExists(dbNode2);
		assertEquals(dbNode1, actual);
		assertNotEquals(dbNode2, actual);
	}

	@DisplayName("Successfully query ADDRESS nodes and identify PROVINCE node already exists")
	@Test
	void validValidateExistsTrueProvince() throws Exception {
		Address dbNode1 = TestDataFactoryUtil.generateAddressProvinceInstance();
		Address dbNode2 = TestDataFactoryUtil.generateAddressProvinceInstance();
		dbNode2.setAddrLine1("124 Fake St");
		Collection<Address> addresses = new ArrayList<>();
		addresses.add(dbNode1);
		when(repo.findByAddrLine1AndCityAndCountry(anyString(), anyString(), anyString())).thenReturn(addresses);
		Address actual = service.validateExists(dbNode2);
		assertEquals(dbNode1, actual);
		assertNotEquals(dbNode2, actual);
	}

	@DisplayName("Successfully query ADDRESS nodes and identify node is unique")
	@Test
	void validValidateExistsFalse() throws Exception {
		Address dbNode1 = TestDataFactoryUtil.generateAddressProvinceInstance();
		Address dbNode2 = TestDataFactoryUtil.generateAddressProvinceInstance();
		dbNode2.setAddrLine1("124 Fake St");
		Collection<Address> addresses = new ArrayList<>();
		when(repo.findByAddrLine1AndCityAndCountry(anyString(), anyString(), anyString())).thenReturn(addresses);
		Address actual = service.validateExists(dbNode2);
		assertEquals(dbNode2, actual);
		assertNotEquals(dbNode1, actual);
	}

}
