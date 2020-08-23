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

import org.pdbcorp.eap.uni.data.model.BaseEntity;
import org.pdbcorp.eap.uni.data.repo.BaseEntityRepository;
import org.pdbcorp.eap.uni.error.EntityNotFoundException;
import org.pdbcorp.eap.uni.service.retrieve.RetrieveEntityDetailsService;
import org.slf4j.Logger;

import reactor.core.publisher.Flux;

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
	public Flux<T> findAll() {
		return repository.findAll();
	}

	private T processMonoOnNext(T entity, T t) {
//		entity = t;
		return (entity = t);
	}

	@Override
	public T findByEntityId(String id) {
		T entity = null;
		repository.findById(id).subscribe(
				//o -> prcoessOnNext(o),
				//e -> processError(e),
				//() -> processCompletion(),
				//d -> processSubscription()
				t -> processMonoOnNext(entity, t)
				);
		return entity;
	}

	public T findByNodeUid(String nodeUid) {
		return repository.findByNodeUid(nodeUid)
				.blockOptional()
				.orElseThrow(
						() -> new EntityNotFoundException(
								String.format("Unable to find entity with nodeUid: %s", nodeUid)));
	}

	public T saveEntity(T entity) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", entity);
		}
		repository.save(entity).subscribe(
				t -> processMonoOnNext(entity, t));
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", entity);
		}
		return entity;
	}

}
