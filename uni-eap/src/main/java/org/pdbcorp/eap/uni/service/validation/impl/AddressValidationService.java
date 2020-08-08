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
public class AddressValidationService {

	private AddressRepository addressRepository;

	@Autowired
	public AddressValidationService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	/**
	 * This method queries the db via the 
	 * {@link org.pdbcorp.eap.uni.data.repo.AddressRepository#findByAddrLine1AndCityAndCountry(String, String, String) findByAddrLine1AndCityAndCountry}
	 * query method for any existing nodes that contain the same <i>addrLine1</i>, <i>city</i>, and <i>country</i> field properties.<br>
	 * If the query returns a collection, then the <i>addrLine2</i>, <i>state</i> or <i>province</i>, and <i>postalCode</i> field properties
	 * of the input parameter object and the returned nodes are compared to see if equivalent. If true, then that means the current ADDRESS
	 * node exists in the db and the method returns the retrieved node. If false, then the node doesn't exist and the input parameter node
	 * is returned.
	 * 
	 * @param address - the object to check the db for.
	 * @return validatedAddress - either the initial object passed in as a parameter, or the ADDRESS node currently present in the db.
	 */
	public Address validateExists(Address address) {
		Collection<Address> dbAddresses = addressRepository.
				findByAddrLine1AndCityAndCountry(address.getAddrLine1(), address.getCity(), address.getCountry());
		
		if(!dbAddresses.isEmpty()) {
			for(Address dbAddress : dbAddresses) {
				if((!StringUtils.isBlank(address.getAddrLine2()) && address.getAddrLine2().equals(dbAddress.getAddrLine2()))
					&& (!StringUtils.isBlank(address.getState()) && address.getState().equals(dbAddress.getState())) ||
						(!StringUtils.isBlank(address.getProvince()) && address.getProvince().equals(dbAddress.getProvince()))
						&& (!StringUtils.isBlank(address.getPostalCode()) && address.getPostalCode().equals(dbAddress.getPostalCode()))) {
					log.debug("Entity to save already exists: {}\n\nFound entity present in db: {}", address, dbAddress);
					return dbAddress;
				}
			}
		}
		return address;
	}

}
