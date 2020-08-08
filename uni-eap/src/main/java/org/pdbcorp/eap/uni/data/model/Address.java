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

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

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
@EqualsAndHashCode(callSuper=false)
@Getter
@NodeEntity(label="ADDRESS")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
public class Address extends GeneratedValueIdEntity {

	@NonNull
	@Property(name="ADDR_LINE_1")
	private String addrLine1;

	@Property(name="ADDR_LINE_2")
	private String addrLine2;

	@NonNull
	@Property(name="CITY")
	private String city;

	@Property(name="STATE")
	private String state;

	@Property(name="PROVINCE")
	private String province;

	@NonNull
	@Property(name="COUNTRY")
	private String country;

	@Property(name="POSTAL_CODE")
	private String postalCode;

	@Property(name="PROPS_UID")
	private String propsUid;

	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("addresses")
	@Relationship(type="RESIDED_ADDRESS", direction=Relationship.INCOMING)
	private Set<Person> persons = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("address")
	@Relationship(type="UNI_ADDRESS", direction=Relationship.INCOMING)
	private University university;

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("Address [");
		if (this.getId() != null) {
			builder.append("id=");
			builder.append(this.getId());
		}
		if (addrLine1 != null) {
			builder.append(", addrLine1=");
			builder.append(addrLine1);
		}
		if (addrLine2 != null) {
			builder.append(", addrLine2=");
			builder.append(addrLine2);
		}
		if (city != null) {
			builder.append(", city=");
			builder.append(city);
		}
		if (state != null) {
			builder.append(", state=");
			builder.append(state);
		}
		if (province != null) {
			builder.append(", province=");
			builder.append(province);
		}
		if (country != null) {
			builder.append(", country=");
			builder.append(country);
		}
		if (postalCode != null) {
			builder.append(", postalCode=");
			builder.append(postalCode);
		}
		if (university != null) {
			builder.append(", university=");
			builder.append(university);
		}
		if (persons != null) {
			builder.append(", persons=");
			builder.append(toString(persons, maxLen));
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
