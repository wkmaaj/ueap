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
package org.pdbcorp.eap.uni.service.impl;

import org.pdbcorp.eap.uni.data.model.University;
import org.pdbcorp.eap.uni.data.repo.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Slf4j
@Service
public class UniversityDetailsService extends BaseEntityDetailsService<University> {

	private UniversityRepository universityRepository;

	@Autowired
	public UniversityDetailsService(UniversityRepository universityRepository) {
		super(universityRepository);
		this.universityRepository = universityRepository;
	}

	public University findByName(String name) {
		return universityRepository.findByName(name);
	}

	public University saveUniversity(University university) {
		if(log.isTraceEnabled()) {
			log.trace("Saving entity: {}", university);
		}
		university = universityRepository.save(university);
		if(log.isDebugEnabled()) {
			log.debug("Saved entity: {}", university);
		}
		return university;
	}

}
