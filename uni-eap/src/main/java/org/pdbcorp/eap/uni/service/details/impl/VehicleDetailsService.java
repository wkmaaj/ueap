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

import org.pdbcorp.eap.uni.data.model.Vehicle;
import org.pdbcorp.eap.uni.data.repo.VehicleRepository;
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
public class VehicleDetailsService extends BaseEntityDetailsService<Vehicle> {

	private VehicleRepository vehicleRepository;
	private EntityPersistenceService<Vehicle> vehiclePersistenceService;

	@Autowired
	public VehicleDetailsService(VehicleRepository vehicleRepository,
			@Qualifier("vehicleValidationService") EntityValidationService<Vehicle> vehicleValidationService,
			@Qualifier("vehiclePersistenceService") EntityPersistenceService<Vehicle> vehiclePersistenceService) {
		
		super(vehicleRepository, vehicleValidationService);
		this.vehicleRepository = vehicleRepository;
		this.vehiclePersistenceService = vehiclePersistenceService;
	}

	@Override
	public Vehicle validateAndSave(Vehicle vehicle) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", vehicle);
		}
		vehicle = super.validateAndSave(vehicle);
		vehicle = vehiclePersistenceService.validateEntityRelationships(vehicle);
		vehicle = vehicleRepository.save(vehicle);
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", vehicle);
		}
		return vehicle;
	}

	public Collection<Vehicle> findByMakeAndModel(String make, String model) {
		return vehicleRepository.findByMakeAndModel(make, model);
	}

}
