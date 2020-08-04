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
@NodeEntity(label="VEHICLE")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
public class Vehicle extends GeneratedValueIdEntity {

	@NonNull
	@Property(name="MAKE")
	private String make;

	@NonNull
	@Property(name="MODEL")
	private String model;

	@NonNull
	@Property(name="YEAR")
	private Long year;

	@NonNull
	@Property(name="LICENSE_PLATE")
	private String licensePlate;

	@NonNull
	@Property(name="VEHICLE_ID_NUMBER")
	private String vin;

	@JsonIgnoreProperties("vehicle")
	@Relationship(type="OWNED_VEHICLE", direction=Relationship.INCOMING)
	private Person person;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vehicle [");
		if (this.getId() != null) {
			builder.append("id=");
			builder.append(this.getId());
		}
		if (make != null) {
			builder.append(", make=");
			builder.append(make);
		}
		if (model != null) {
			builder.append(", model=");
			builder.append(model);
		}
		if (year != null) {
			builder.append(", year=");
			builder.append(year);
		}
		if (licensePlate != null) {
			builder.append(", licensePlate=");
			builder.append(licensePlate);
		}
		if (vin != null) {
			builder.append(", vin=");
			builder.append(vin);
		}
		if (person != null) {
			builder.append(", person=");
			builder.append(person);
		}
		builder.append("]");
		return builder.toString();
	}

}
