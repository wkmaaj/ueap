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

import java.util.Collection;

import javax.ws.rs.core.Response;

import org.pdbcorp.eap.uni.controller.rest.StudentRestController;
import org.pdbcorp.eap.uni.data.model.Student;
import org.pdbcorp.eap.uni.service.impl.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author jaradat-pdb
 */
@Controller
public class StudentRestControllerImpl implements StudentRestController {

	private StudentDetailsService studentDetailsService;

	@Autowired
	public StudentRestControllerImpl(StudentDetailsService studentDetailsService) {
		this.studentDetailsService = studentDetailsService;
	}

	@Override
	public Collection<Student> findAll() {
		return studentDetailsService.findAll();
	}

	@Override
	public Response findByName(String name) {
		return Response.ok(studentDetailsService.findByName(name)).build();
	}

	@Override
	public Response saveStudent(Student student) {
		return Response.ok(studentDetailsService.saveStudent(student)).build();
	}

}