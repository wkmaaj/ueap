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

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Slf4j
@Service
public class AddressExistsValidator {

	private AddressRepository addressRepository;

	@Autowired
	public AddressExistsValidator(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public boolean validateSaveAddress(Address address) {
		boolean exists = false;
		Collection<Address> dbAddresses = addressRepository.
				findByAddrLine1AndAddrLine2AndCityAndCountry(address.getAddrLine1(),
						address.getAddrLine2(), address.getCity(), address.getCountry());
		if(!dbAddresses.isEmpty()) {
			for(Address dbAddress : dbAddresses) {
				if((!StringUtils.isBlank(address.getState()) && address.getState().equals(dbAddress.getState())) ||
						(!StringUtils.isBlank(address.getProvince()) && address.getProvince().equals(dbAddress.getProvince()))) {
					log.debug("Entity to save: {}\nFound entity: {}", address, dbAddress);
					exists = true;
				}
			}
		}
		return exists;
	}

}
