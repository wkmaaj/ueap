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
package org.pdbcorp.eap.uni.service.generation.impl;

import org.apache.commons.lang3.StringUtils;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.service.generation.EntityNodeUidGenerationService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Slf4j
@Service
class AddressNodeUidGenerationService implements EntityNodeUidGenerationService<Address>{

	/**
	 * This method constructs a <i>unique identifying</i> {@link java.lang.String String} based on the properties
	 * of the <code>ADDRESS</code> node. This {@link java.lang.String String} will be used in comparing and determining
	 * if an <code>ADDRESS</code> node already exists in the datastore with all the same field properties (i.e.
	 * <code>addrLine1</code>, <code>addrLine2</code>, <code>city</code>, <code>state</code>, <code>province</code>,
	 * <code>country</code>, <code>postalCode</code>). This method does not factor in relational properties (i.e. ignores
	 * <code>persons</code> and <code>university</code> properties).
	 * 
	 * @param address - the <code>ADDRESS</code> object to construct the unique ID for.
	 * @return String - the unique ID for the node based off of its properties.
	 */
	@Override
	public String generateNodeUid(Address address) {
		StringBuilder stringBuilder = new StringBuilder();
		if(log.isTraceEnabled()) {
			log.trace("Building propsUid for entity: {}", address);
		}
		
		if(!StringUtils.isBlank(address.getAddrLine1())) {
			stringBuilder.append(address.getAddrLine1());
			if(!StringUtils.isBlank(address.getAddrLine2())) {
				stringBuilder.append(", ").append(address.getAddrLine2());
			}
			if(!StringUtils.isBlank(address.getCity())) {
				stringBuilder.append(", ").append(address.getCity());
			}
			if(!StringUtils.isBlank(address.getState())) {
				stringBuilder.append(", ").append(address.getState());
			}
			if(!StringUtils.isBlank(address.getProvince())) {
				stringBuilder.append(", ").append(address.getProvince());
			}
			if(!StringUtils.isBlank(address.getCountry())) {
				stringBuilder.append(", ").append(address.getCountry());
			}
			if(!StringUtils.isBlank(address.getPostalCode())) {
				stringBuilder.append(", ").append(address.getPostalCode());
			}
			if(log.isDebugEnabled()) {
				log.debug("Successfully constructed propsUid: {}", stringBuilder.toString());
			}
		}
		
		return stringBuilder.toString();
	}

}
