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
package org.pdbcorp.eap.uni.service.details.impl;

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
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.repo.AddressRepository;
import org.pdbcorp.eap.uni.service.persist.EntityPersistenceService;
import org.pdbcorp.eap.uni.service.validation.EntityValidationService;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
class AddressDetailsServiceTest {

	@Mock
	private AddressRepository repository;
	@Mock
	private EntityValidationService<Address> validator;
	@Mock
	private EntityPersistenceService<Address> persistence;

	@InjectMocks
	private AddressDetailsService service = new AddressDetailsService(repository, validator, persistence);

	@DisplayName("Successfully query ADDRESS nodes using ADDR_LINE_1")
	@Test
	void validFindByAddrLine1Test() throws Exception {
		Address expected = TestDataFactoryUtil.generateAddressStateInstance();
		when(repository.findByAddrLine1(expected.getAddrLine1())).thenReturn(Arrays.asList(expected));
		Collection<Address> result = service.findByAddrLine1(expected.getAddrLine1());
		assertTrue(result.contains(expected));
	}

	@DisplayName("Successfully save an ADDRESS node via the details service")
	@Test
	void validValidateAndSaveTest() throws Exception {
		Address expected = TestDataFactoryUtil.generateAddressProvinceInstance();
		when(validator.validateNodeUid(expected)).thenReturn(expected);
		when(repository.save(any())).thenReturn(expected);
		assertEquals(expected, service.validateAndSave(expected));
	}

}
