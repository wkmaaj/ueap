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
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.neo4j.ogm.annotation.typeconversion.EnumString;
import org.pdbcorp.eap.uni.data.constant.GenderEnum;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@NodeEntity(label="PERSON")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
public class Person extends GeneratedValueIdEntity {

	@NonNull
	@Property(name="FIRST_NAME")
	private String fname;

	@Property(name="MIDDLE_NAME")
	private String mname;

	@NonNull
	@Property(name="LAST_NAME")
	private String lname;

	@DateLong
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Property(name="BIRTH_DATE")
	private Date dob;

	@Property(name="GENDER")
	@EnumString(value=GenderEnum.class)
	private GenderEnum gender;

	@EqualsAndHashCode.Exclude
	@Relationship(type="CURRENT_OCCUPATION", direction=Relationship.INCOMING)
	private OccupationEntity occupation;

	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("persons")
	@Relationship(type="RESIDED_ADDRESS")
	private Set<Address> addresses = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("person")
	@Relationship(type="OWNED_VEHICLE")
	private Vehicle vehicle;

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("Person [");
		if (this.getId() != null) {
			builder.append("id=");
			builder.append(this.getId());
		}
		if (fname != null) {
			builder.append(", fname=");
			builder.append(fname);
		}
		if (mname != null) {
			builder.append(", mname=");
			builder.append(mname);
		}
		if (lname != null) {
			builder.append(", lname=");
			builder.append(lname);
		}
		if (dob != null) {
			builder.append(", dob=");
			builder.append(dob);
		}
		if (gender != null) {
			builder.append(", gender=");
			builder.append(gender.getCode());
		}
		if (occupation != null) {
			builder.append(", occupationId=");
			builder.append(occupation.getId());
		}
		if (addresses != null) {
			builder.append(", address=");
			builder.append(toString(addresses, maxLen));
		}
		if (vehicle != null) {
			builder.append(", vehicleId=");
			builder.append(vehicle.getId());
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
