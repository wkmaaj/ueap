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
package org.pdbcorp.eap.uni.data.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.id.UuidStrategy;

import lombok.NonNull;

/**
 * 
 * @author jaradat-pdb
 */
abstract class GeneratedValueIdEntity implements BaseEntity {

	@Id
	@GeneratedValue(strategy=UuidStrategy.class)
	private String id;

	@Index(unique=true)
	@NonNull
	@Property(name="NODE_UID")
	private String nodeUid;

	public String getId() {
		return id;
	}

	public String getNodeUid() {
		return nodeUid;
	}

	public void setNodeUid(String nodeUid) {
		this.nodeUid = nodeUid;
	}

}
