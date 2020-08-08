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

import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.repo.AddressRepository;
import org.pdbcorp.eap.uni.service.generation.impl.AddressUniqueStringGenerationService;
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
	private AddressUniqueStringGenerationService addressUniqueStringGenerationService;

	@Autowired
	public AddressValidationService(
		AddressRepository addressRepository, AddressUniqueStringGenerationService addressUniqueStringGenerationService) {
		
		this.addressRepository = addressRepository;
		this.addressUniqueStringGenerationService = addressUniqueStringGenerationService;
	}

	/**
	 * This method queries the db via the 
	 * {@link org.pdbcorp.eap.uni.data.repo.AddressRepository#findByAddrLine1AndCityAndCountry(String, String, String) findByAddrLine1AndCityAndCountry}
	 * query method for any existing nodes that contain the same <code>addrLine1</code>, <code>city</code>,
	 * and <code>country</code> field properties.<br>
	 * If the query returns a collection, then the {@link org.pdbcorp.eap.uni.data.model.Address#getPropsUid() propsUid} 
	 * of the input parameter object and the returned nodes are compared to see if equivalent. If a returned node is found
	 * to have an equivalent {@link org.pdbcorp.eap.uni.data.model.Address#getPropsUid() propsUid}, then that node is returned
	 * to the method caller. Else, the input parameter object is passed back to the method caller with its
	 * {@link org.pdbcorp.eap.uni.data.model.Address#getPropsUid() propsUid} property set.
	 * 
	 * @param address - the object to check the db for.
	 * @return Address - either the initial object passed in as a parameter, or the ADDRESS node currently present in the db.
	 */
	public Address validateExists(Address address) {
		address.setPropsUid(addressUniqueStringGenerationService.generateUniqueIdString(address));
		Collection<Address> dbNodes = addressRepository.
				findByAddrLine1AndCityAndCountry(address.getAddrLine1(), address.getCity(), address.getCountry());
		if(!dbNodes.isEmpty()) {
			for(Address dbNode : dbNodes) {
				if(address.getPropsUid().equals(dbNode.getPropsUid())) {
					if(log.isDebugEnabled()) {
						log.debug("Identified existing node with matching propsUid: {}", dbNode);
					}
					return dbNode;
				}
			}
		}
		return address;
	}

}
