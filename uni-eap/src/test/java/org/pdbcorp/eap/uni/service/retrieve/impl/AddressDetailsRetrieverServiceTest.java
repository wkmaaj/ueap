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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.repo.AddressRepository;
import org.pdbcorp.eap.uni.util.TestDataFactoryUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jaradat-pdb
 */
@ExtendWith(MockitoExtension.class)
@Slf4j
class AddressDetailsRetrieverServiceTest {

	@Mock
	private AddressRepository repository;
	@Mock
	private Mono<Address> mockMono;

	@InjectMocks
	private AddressDetailsRetrieverService service = new AddressDetailsRetrieverService(repository);

	@DisplayName("Successfully query ADDRESS nodes using ADDR_LINE_1")
	@Test
	void validFindByAddrLine1Test() throws Exception {
		Address expected = TestDataFactoryUtil.generateAddressStateInstance();
		Flux<Address> flux = Flux.just(expected);
		when(repository.findByAddrLine1(expected.getAddrLine1())).thenReturn(flux);
		Flux<Address> result = service.findByAddrLine1(expected.getAddrLine1());
		result.hasElement(expected).subscribe(
				value -> assertTrue(value),
				error -> log.error("{}", error),
				() -> log.warn("Completed without value nor error"));
	}

	@DisplayName("Successfully save an ADDRESS node via the details service")
	@Test
	void validValidateAndSaveTest() throws Exception {
		Address expected = TestDataFactoryUtil.generateAddressProvinceInstance();
		when(repository.save(any())).thenReturn(mockMono);
		assertEquals(expected, service.saveEntity(expected));
	}

}
