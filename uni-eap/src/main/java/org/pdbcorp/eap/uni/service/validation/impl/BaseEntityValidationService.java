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

import java.util.Optional;

import org.pdbcorp.eap.uni.data.model.BaseEntity;
import org.pdbcorp.eap.uni.data.repo.BaseEntityRepository;
import org.pdbcorp.eap.uni.service.generation.EntityNodeUidGenerationService;
import org.pdbcorp.eap.uni.service.validation.EntityValidationService;
import org.slf4j.Logger;

/**
 * 
 * @author jaradat-pdb
 */
class BaseEntityValidationService<T extends BaseEntity> implements EntityValidationService<T> {

	private BaseEntityRepository<T> baseEntityRepository;
	private EntityNodeUidGenerationService<T> entityNodeUidGenerationService;
	private Logger log;

	BaseEntityValidationService(
			BaseEntityRepository<T> baseEntityRepository,
			EntityNodeUidGenerationService<T> entityNodeUidGenerationService,
			Logger log) {
		
		this.baseEntityRepository = baseEntityRepository;
		this.entityNodeUidGenerationService = entityNodeUidGenerationService;
		this.log = log;
	}

	/**
	 * This method initially generates the {@link org.pdbcorp.eap.uni.data.model.BaseEntity#getNodeUid() nodeUid}
	 * property value of an entity object passed to the service by a method caller. The datastore is then queried
	 * using the newly generated {@link org.pdbcorp.eap.uni.data.model.BaseEntity#getNodeUid() nodeUid} property
	 * value:
	 * <li>if the datastore call returns a node entity, then the service will return the retrieved node
	 * entity to the method caller.</li>
	 * <li>if the datastore call returns an empty result, then the service will return the input entity object
	 * with its {@link org.pdbcorp.eap.uni.data.model.BaseEntity#getNodeUid() nodeUid} property set.</li>
	 * 
	 * @param T - the input entity object to check the datastore for.
	 * @return Person - either the initial entity (input parameter entity), or the entity node found in the datastore.
	 */
	@Override
	public T validateNodeUid(T t) {
		t.setNodeUid(entityNodeUidGenerationService.generateNodeUid(t));
		Optional<T> dbRes = baseEntityRepository.findByNodeUid(t.getNodeUid());
		if(dbRes.isEmpty()) {
			log.info("Identified unique node with no currently existing nodeUid: {}", t);
			return t;
		} else {
			if(log.isDebugEnabled()) {
				log.debug("Identified existing node with matching nodeUid: {}", dbRes.get());
			}
			return dbRes.get();
		}
	}

}
