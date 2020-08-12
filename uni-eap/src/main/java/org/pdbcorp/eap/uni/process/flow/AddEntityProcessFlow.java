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
package org.pdbcorp.eap.uni.process.flow;

import org.pdbcorp.eap.uni.data.model.BaseEntity;
import org.pdbcorp.eap.uni.process.ProcessFlow;
import org.pdbcorp.eap.uni.service.generate.GenerateNodeUidService;
import org.pdbcorp.eap.uni.service.retrieve.RetrieveEntityDetailsService;
import org.pdbcorp.eap.uni.service.validate.ValidateEntityRelationshipsService;
import org.pdbcorp.eap.uni.service.validate.ValidateNodeUidService;
import org.slf4j.Logger;

/**
 * 
 * @author jaradat-pdb
 */
public abstract class AddEntityProcessFlow<T extends BaseEntity> implements ProcessFlow<T> {

	private RetrieveEntityDetailsService<T> retrieveEntityDetailsService;
	private GenerateNodeUidService<T> generateNodeUidService;
	private ValidateEntityRelationshipsService<T> validateEntityRelationshipsService;
	private ValidateNodeUidService<T> validateNodeUidService;
	private Logger log;

	protected AddEntityProcessFlow(
			RetrieveEntityDetailsService<T> retrieveEntityDetailsService,
			GenerateNodeUidService<T> generateNodeUidService,
			ValidateEntityRelationshipsService<T> validateEntityRelationshipsService,
			ValidateNodeUidService<T> validateNodeUidService,
			Logger log) {
		
		this.retrieveEntityDetailsService = retrieveEntityDetailsService;
		this.generateNodeUidService = generateNodeUidService;
		this.validateEntityRelationshipsService = validateEntityRelationshipsService;
		this.validateNodeUidService = validateNodeUidService;
		this.log = log;
	}

	@Override
	public T execute(T entity) {
		if(log.isTraceEnabled()) {
			log.trace("Starting {} service call for entity: {}", this.getClass().getSimpleName(), entity);
		}
		entity.setNodeUid(generateNodeUidService.generateNodeUid(entity));
		entity = validateNodeUidService.validateNodeUid(entity);
		entity = validateEntityRelationshipsService.validateEntityRelationships(entity);
		entity = retrieveEntityDetailsService.saveEntity(entity);
		if(log.isDebugEnabled()) {
			log.debug("Completed {} service call for entity: {}", this.getClass().getSimpleName(), entity);
		}
		return entity;
	}

}
