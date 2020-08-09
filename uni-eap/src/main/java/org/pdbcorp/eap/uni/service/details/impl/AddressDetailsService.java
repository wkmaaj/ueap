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

import java.util.Collection;

import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.repo.AddressRepository;
import org.pdbcorp.eap.uni.service.persist.EntityPersistenceService;
import org.pdbcorp.eap.uni.service.validation.EntityValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Slf4j
@Service
public class AddressDetailsService extends BaseEntityDetailsService<Address> {

	private AddressRepository addressRepository;
	private EntityPersistenceService<Address> addressPersistenceService;

	@Autowired
	public AddressDetailsService(AddressRepository addressRepository,
			@Qualifier("addressValidationService") EntityValidationService<Address> addressValidationService,
			@Qualifier("addressPersistenceService") EntityPersistenceService<Address> addressPersistenceService) {
		
		super(addressRepository, addressValidationService);
		this.addressRepository = addressRepository;
		this.addressPersistenceService = addressPersistenceService;
	}

	@Override
	public Address validateAndSave(Address address) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", address);
		}
		address = super.validateAndSave(address);
		address = addressPersistenceService.validateEntityRelationships(address);
		address = addressRepository.save(address);
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", address);
		}
		return address;
	}

	public Collection<Address> findByAddrLine1(String addrLine1) {
		return addressRepository.findByAddrLine1(addrLine1);
	}

}