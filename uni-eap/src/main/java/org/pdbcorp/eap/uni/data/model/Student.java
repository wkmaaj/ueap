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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Property;
import org.neo4j.springframework.data.core.schema.Relationship;

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
@Node(primaryLabel = "STUDENT")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
public class Student extends OccupationEntity {

	@NonNull
	@Property(name = "NAME")
	private String name;

	@EqualsAndHashCode.Exclude
//	@JsonIgnoreProperties("student")
	@Relationship(type = "ENROLLED", direction = Relationship.Direction.OUTGOING)
	private Map<Course, Enrollment> enrolledCourses = new HashMap<>();

//	@Relationship(type="BUDDY", direction=Relationship.INCOMING)
//	private Set<StudyBuddy> studyBuddies;

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("Student [");
		if (this.getId() != null) {
			builder.append("id=");
			builder.append(this.getId());
		}
		if (name != null) {
			builder.append(", name=");
			builder.append(name);
		}
		if (this.getPerson() != null) {
			builder.append(", person=");
			builder.append(this.getPerson());
		}
		if (enrolledCourses != null) {
			builder.append(", enrollments=");
			builder.append(toString(enrolledCourses, maxLen));
		}
		builder.append("]");
		return builder.toString();
	}

	private String toString(Map<?, ?> map, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext() && i < maxLen; i++) {
			Object key = iterator.next();
			if (i > 0)
				builder.append(", ");
			builder.append("{").append(key).append(": ").append(map.get(key)).append("}");
		}
		builder.append("]");
		return builder.toString();
	}

}
