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

import java.util.Collection;

import javax.ws.rs.core.Response;

import org.pdbcorp.eap.uni.controller.rest.AddressRestController;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.data.model.University;
import org.pdbcorp.eap.uni.service.impl.AddressDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author jaradat-pdb
 */
@Controller
public class AddressRestControllerImpl implements AddressRestController {

	private AddressDetailsService addressDetailsService;

	@Autowired
	public AddressRestControllerImpl(AddressDetailsService addressDetailsService) {
		this.addressDetailsService = addressDetailsService;
	}

	@Override
	public Collection<Address> findAll() {
		return addressDetailsService.findAll();
	}

	@Override
	public Response findByAddrLine1(String addrLine1) {
		return Response.ok(addressDetailsService.findByAddrLine1(addrLine1)).build();
	}

	@Override
	public Response saveAddress(Address address) {
		return Response.ok(addressDetailsService.saveAddress(address)).build();
	}

	@Override
	public Response updateWithPerson(String id, Person person) {
		Address address = addressDetailsService.findByEntityId(id);
		address.getPersons().add(person);
		return Response.ok(addressDetailsService.saveAddress(address)).build();
	}

	@Override
	public Response updateWithUniversity(String id, University university) {
		Address address = addressDetailsService.findByEntityId(id);
		address.setUniversity(university);
		return Response.ok(addressDetailsService.saveAddress(address)).build();
	}

}
