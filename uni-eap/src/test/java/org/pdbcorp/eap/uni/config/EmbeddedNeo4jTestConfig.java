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
package org.pdbcorp.eap.uni.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.test.context.ActiveProfiles;

/**
 * 
 * @author jaradat-pdb
 */
@ActiveProfiles("test")
@Configuration
public class EmbeddedNeo4jTestConfig {

	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory("org.pdbcorp.eap.uni");
	}

	@Bean
	public Neo4jTransactionManager transactionManager() throws Exception {
		return new Neo4jTransactionManager(sessionFactory());
	}

}
