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
package org.pdbcorp.eap.uni.controller.rest.impl;

import javax.ws.rs.core.Response;

import org.pdbcorp.eap.uni.controller.rest.AddressRestController;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.data.model.University;
import org.pdbcorp.eap.uni.process.flow.AddEntityProcessFlow;
import org.pdbcorp.eap.uni.service.retrieve.impl.AddressDetailsRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;

/**
 * 
 * @author jaradat-pdb
 */
@Controller
public class AddressRestControllerImpl implements AddressRestController {

	private AddressDetailsRetrieverService addressDetailsRetrieverService;
	private AddEntityProcessFlow<Address> addAddressProcessFlow;

	@Autowired
	public AddressRestControllerImpl(
			AddressDetailsRetrieverService addressDetailsRetrieverService,
			@Qualifier("addAddressProcessFlow") AddEntityProcessFlow<Address> addAddressProcessFlow) {
		
		this.addressDetailsRetrieverService = addressDetailsRetrieverService;
		this.addAddressProcessFlow = addAddressProcessFlow;
	}

	@Override
	public Flux<Address> findAll() {
		return addressDetailsRetrieverService.findAll();
	}

	@Override
	public Response findByAddrLine1(String addrLine1) {
		return Response.ok(addressDetailsRetrieverService.findByAddrLine1(addrLine1)).build();
	}

	@Override
	public Response saveAddress(Address address) {
		return Response.ok(addAddressProcessFlow.execute(address)).build();
	}

	@Override
	public Response updateWithPerson(String id, Person person) {
		Address address = addressDetailsRetrieverService.findByEntityId(id);
		address.getPersons().add(person);
		return Response.ok(addressDetailsRetrieverService.saveEntity(address)).build();
	}

	@Override
	public Response updateWithUniversity(String id, University university) {
		Address address = addressDetailsRetrieverService.findByEntityId(id);
		address.setUniversity(university);
		return Response.ok(addressDetailsRetrieverService.saveEntity(address)).build();
	}

}
