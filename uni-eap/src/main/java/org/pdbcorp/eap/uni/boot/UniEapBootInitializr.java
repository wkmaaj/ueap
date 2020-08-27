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
package org.pdbcorp.eap.uni.boot;

import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.repo.AddressRepository;
import org.pdbcorp.eap.uni.data.repo.CourseRepository;
import org.pdbcorp.eap.uni.data.repo.DepartmentRepository;
import org.pdbcorp.eap.uni.data.repo.EnrollmentRepository;
import org.pdbcorp.eap.uni.data.repo.PersonRepository;
import org.pdbcorp.eap.uni.data.repo.StudentRepository;
import org.pdbcorp.eap.uni.data.repo.SubjectRepository;
import org.pdbcorp.eap.uni.data.repo.TeacherRepository;
import org.pdbcorp.eap.uni.data.repo.UniversityRepository;
import org.pdbcorp.eap.uni.data.repo.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Component
@Profile("local")
@Slf4j
public class UniEapBootInitializr implements CommandLineRunner {

	private AddressRepository addressRepository;
	private CourseRepository courseRepository;
	private DepartmentRepository departmentRepository;
	private EnrollmentRepository enrollmentRepository;
	private PersonRepository personRepository;
	private StudentRepository studentRepository;
	private SubjectRepository subjectRepository;
	private TeacherRepository teacherRepository;
	private UniversityRepository universityRepository;
	private VehicleRepository vehicleRepository;

	@Autowired
	public UniEapBootInitializr(AddressRepository addressRepository,
			CourseRepository courseRepository, DepartmentRepository departmentRepository, EnrollmentRepository enrollmentRepository,
			PersonRepository personRepository, StudentRepository studentRepository, SubjectRepository subjectRepository,
			TeacherRepository teacherRepository, UniversityRepository universityRepository, VehicleRepository vehicleRepository) {
		
		this.addressRepository = addressRepository;
		this.courseRepository = courseRepository;
		this.departmentRepository = departmentRepository;
		this.enrollmentRepository = enrollmentRepository;
		this.personRepository = personRepository;
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.teacherRepository = teacherRepository;
		this.universityRepository = universityRepository;
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Address address = generateAddressEntity();
	}

	private Address generateAddressEntity() {
		return addressRepository.save(new Address("123 Fake St", "Irbid", "Jordan")).block();
	}

}
