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
package org.pdbcorp.eap.uni.controller.rest.impl;

import javax.ws.rs.core.Response;

import org.pdbcorp.eap.uni.controller.rest.UniversityRestController;
import org.pdbcorp.eap.uni.data.model.University;
import org.pdbcorp.eap.uni.process.flow.AddEntityProcessFlow;
import org.pdbcorp.eap.uni.service.retrieve.impl.UniversityDetailsRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;

/**
 * 
 * @author jaradat-pdb
 */
@Controller
public class UniversityRestControllerImpl implements UniversityRestController {

	private UniversityDetailsRetrieverService universityDetailsRetrieverService;
	private AddEntityProcessFlow<University> addUniversityProcessFlow;

	@Autowired
	public UniversityRestControllerImpl(
			UniversityDetailsRetrieverService universityDetailsRetrieverService,
			@Qualifier("addUniversityProcessFlow") AddEntityProcessFlow<University> addUniversityProcessFlow) {
		
		this.universityDetailsRetrieverService = universityDetailsRetrieverService;
		this.addUniversityProcessFlow = addUniversityProcessFlow;
	}

	@Override
	public Flux<University> findAll() {
		return universityDetailsRetrieverService.findAll();
	}

	@Override
	public Response findByName(String name) {
		return Response.ok(universityDetailsRetrieverService.findByName(name)).build();
	}

	@Override
	public Response saveUniversity(University university) {
		return Response.ok(addUniversityProcessFlow.execute(university)).build();
	}

}
