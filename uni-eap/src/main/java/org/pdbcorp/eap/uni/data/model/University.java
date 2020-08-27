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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Property;
import org.neo4j.springframework.data.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author jaradat-pdb
 */
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Node(primaryLabel = "UNIVERSITY")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
public class University extends GeneratedValueIdEntity {

	@NonNull
	@Property(name = "NAME")
	private String name;

	@JsonIgnoreProperties("university")
	@Relationship(type = "UNI_ADDRESS", direction = Relationship.Direction.OUTGOING)
	private Address address;

	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("university")
	@Relationship(type = "UNI_DEPARTMENT", direction = Relationship.Direction.INCOMING)
	private Set<Department> departments = new HashSet<>();

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("University [");
		if (this.getId() != null) {
			builder.append("id=");
			builder.append(this.getId());
		}
		if (name != null) {
			builder.append(", name=");
			builder.append(name);
		}
		if (address != null) {
			builder.append(", addressId=");
			builder.append(address.getId());
		}
		if (departments != null) {
			builder.append(", departments=");
			builder.append(toString(departments, maxLen));
		}
		builder.append("]");
		return builder.toString();
	}

	private String toString(Collection<? extends GeneratedValueIdEntity> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<? extends GeneratedValueIdEntity> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next().getId());
		}
		builder.append("]");
		return builder.toString();
	}

}
