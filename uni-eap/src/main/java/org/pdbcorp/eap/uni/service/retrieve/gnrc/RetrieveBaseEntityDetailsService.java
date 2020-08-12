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
package org.pdbcorp.eap.uni.service.retrieve.gnrc;

import java.util.Collection;

import org.pdbcorp.eap.uni.data.model.BaseEntity;
import org.pdbcorp.eap.uni.data.repo.BaseEntityRepository;
import org.pdbcorp.eap.uni.error.EntityNotFoundException;
import org.pdbcorp.eap.uni.service.retrieve.RetrieveEntityDetailsService;
import org.slf4j.Logger;

/**
 * 
 * @author jaradat-pdb
 */
public abstract class RetrieveBaseEntityDetailsService<T extends BaseEntity> implements RetrieveEntityDetailsService<T> {

	private BaseEntityRepository<T> repository;
	private Logger log;

	protected RetrieveBaseEntityDetailsService(BaseEntityRepository<T> repository, Logger log) {
		this.repository = repository;
		this.log = log;
	}

	@Override
	public Collection<T> findAll() {
		return (Collection<T>) repository.findAll();
	}

	@Override
	public T findByEntityId(String id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find entity with id: %s", id)));
	}

	public T findByNodeUid(String nodeUid) {
		return repository.findByNodeUid(nodeUid).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find entity with nodeUid: %s", nodeUid)));
	}

	public T saveEntity(T entity) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", entity);
		}
		entity = repository.save(entity);
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", entity);
		}
		return repository.save(entity);
	}

}
