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

import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

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
@NoArgsConstructor
@RelationshipEntity(type="ENROLLED")
@RequiredArgsConstructor
@Setter
public class Enrollment extends GeneratedValueIdEntity {

	@EqualsAndHashCode.Exclude
	@StartNode
	private Student student;

	@EqualsAndHashCode.Exclude
	@EndNode
	private Course course;

	@NonNull
	@DateLong
	private Date enrolledDate;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enrollment [");
		if (this.getId() != null) {
			builder.append("id=");
			builder.append(this.getId());
		}
		if (enrolledDate != null) {
			builder.append(", enrolledDate=");
			builder.append(enrolledDate);
		}
		if (student != null) {
			builder.append(", studentId=");
			builder.append(student.getId());
		}
		if (course != null) {
			builder.append(", courseId=");
			builder.append(course.getId());
		}
		builder.append("]");
		return builder.toString();
	}

}
