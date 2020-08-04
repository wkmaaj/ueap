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
package org.pdbcorp.eap.uni.data.constant;

import lombok.Getter;
import lombok.ToString;

/**
 * 
 * @author jaradat-pdb
 */
@Getter
@ToString
public enum GenderEnum {

	MALE("M", "MALE", "Male"),
	FEMALE("F", "FEMALE", "Female"),
	NOT_SPECIFIED("U", "N/A", "Not Specified");

	private final String code;
	private final String name;
	private final String desc;

	GenderEnum(final String code, final String name, final String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}

}
