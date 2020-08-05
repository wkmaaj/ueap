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
package org.pdbcorp.eap.uni.service.impl;

import java.util.Collection;

import org.pdbcorp.eap.uni.data.model.BaseEntity;
import org.pdbcorp.eap.uni.error.EntityNotFoundException;
import org.pdbcorp.eap.uni.service.EntityDetailsService;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * 
 * @author jaradat-pdb
 */
abstract class BaseEntityDetailsService<T extends BaseEntity> implements EntityDetailsService<T> {

	private Neo4jRepository<T, String> repository;

	BaseEntityDetailsService(Neo4jRepository<T, String> repository) {
		this.repository = repository;
	}

	@Override
	public Collection<T> findAll() {
		return (Collection<T>) repository.findAll();
	}

	@Override
	public T findByEntityId(String id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find entity with id: %s", id)));
	}

}
