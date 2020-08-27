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
package org.pdbcorp.eap.uni.data.repo;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.pdbcorp.eap.uni.data.model.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import reactor.core.publisher.Mono;

/**
 * 
 * @author jaradat-pdb
 */
@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity> extends ReactiveNeo4jRepository<T, String> {

	public Mono<T> findByNodeUid(@Param("nodeUid") String nodeUid);

}
