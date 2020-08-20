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
package org.pdbcorp.eap.uni.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.pdbcorp.eap.uni.data.constant.GenderEnum;
import org.pdbcorp.eap.uni.data.model.Address;
import org.pdbcorp.eap.uni.data.model.Course;
import org.pdbcorp.eap.uni.data.model.Department;
import org.pdbcorp.eap.uni.data.model.Enrollment;
import org.pdbcorp.eap.uni.data.model.Person;
import org.pdbcorp.eap.uni.data.model.Student;
import org.pdbcorp.eap.uni.data.model.Subject;
import org.pdbcorp.eap.uni.data.model.Teacher;
import org.pdbcorp.eap.uni.data.model.University;
import org.pdbcorp.eap.uni.data.model.Vehicle;
import org.testcontainers.containers.Neo4jContainer;

/**
 * 
 * @author jaradat-pdb
 */
public final class TestDataFactoryUtil {

	public static final boolean checkContainerRunning(Neo4jContainer<?> container) {
		return container.isRunning();
	}

	public static final Address generateAddressInstanceWithPerson() throws ParseException {
		Address address = generateAddressStateInstance();
		Person person = generatePersonInstance();
		person.getAddresses().add(address);
		address.getPersons().add(person);
		return address;
	}

	public static final Address generateAddressInstanceWithUniversity() {
		Address address = generateAddressStateInstance();
		address.setUniversity(generateUniversityInstance());
		address.getUniversity().setAddress(address);
		return address;
	}

	public static final Address generateAddressStateInstance() {
		Address address = new Address();
		address.setAddrLine1("123 Fake St");
		address.setAddrLine2("Unit P");
		address.setCity("Washington");
		address.setState("D.C.");
		address.setCountry("USA");
		address.setPostalCode("20001");
		return address;
	}

	public static final Address generateAddressProvinceInstance() {
		Address address = new Address();
		address.setAddrLine1("321 Real Deal Blvd");
		address.setAddrLine2("#69");
		address.setCity("Toronto");
		address.setProvince("Ontario");
		address.setCountry("CA");
		address.setPostalCode("001AE");
		return address;
	}

	public static final Course generateCourseInstance() {
		Course course = new Course();
		course.setName("Test Course");
		return course;
	}

	public static final Department generateDepartmentInstanceWithUniversity() {
		Department department = generateDepartmentInstance();
		department.setUniversity(generateUniversityInstance());
		department.getUniversity().getDepartments().add(department);
		return department;
	}

	public static final Department generateDepartmentInstance() {
		Department department = new Department();
		department.setName("Test Department");
		return department;
	}

	public static final Enrollment generateEnrollmentInstance() {
		return new Enrollment(new Date());
	}

	public static final Person generatePersonInstanceWithAddressStudentVehicle() throws ParseException {
		Person person = generatePersonInstanceWithAddressStudent();
		person.setVehicle(generateVehicleInstance());
		person.getVehicle().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstanceWithAddressStudent() throws ParseException {
		Person person = generatePersonInstanceWithAddress();
		person.setOccupation(generateStudentInstance());
		person.getOccupation().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstanceWithAddressTeacherVehicle() throws ParseException {
		Person person = generatePersonInstanceWithAddressTeacher();
		person.setVehicle(generateVehicleInstance());
		person.getVehicle().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstanceWithAddressTeacher() throws ParseException {
		Person person = generatePersonInstanceWithAddress();
		person.setOccupation(generateTeacherInstance());
		person.getOccupation().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstanceWithAddressVehicle() throws ParseException {
		Person person = generatePersonInstanceWithAddress();
		person.setVehicle(generateVehicleInstance());
		person.getVehicle().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstanceWithAddress() throws ParseException {
		Person person = generatePersonInstance();
		Address address = generateAddressStateInstance();
		address.getPersons().add(person);
		person.getAddresses().add(address);
		return person;
	}

	public static final Person generatePersonInstanceWithStudentVehicle() throws ParseException {
		Person person = generatePersonInstanceWithStudent();
		person.setVehicle(generateVehicleInstance());
		person.getVehicle().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstanceWithStudent() throws ParseException {
		Person person = generatePersonInstance();
		person.setOccupation(generateStudentInstance());
		person.getOccupation().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstanceWithTeacherVehicle() throws ParseException {
		Person person = generatePersonInstanceWithTeacher();
		person.setVehicle(generateVehicleInstance());
		person.getVehicle().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstanceWithTeacher() throws ParseException {
		Person person = generatePersonInstance();
		person.setOccupation(generateTeacherInstance());
		person.getOccupation().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstanceWithVehicle() throws ParseException {
		Person person = generatePersonInstance();
		person.setVehicle(generateVehicleInstance());
		person.getVehicle().setPerson(person);
		return person;
	}

	public static final Person generatePersonInstance() throws ParseException {
		Person person = new Person();
		person.setFname("Test");
		person.setMname("Person");
		person.setLname("Data");
		person.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("14/08/1990"));
		person.setGender(GenderEnum.NOT_SPECIFIED);
		return person;
	}

	public static final Student generateStudentInstanceWithPerson() throws ParseException {
		Student student = generateStudentInstance();
		student.setPerson(generatePersonInstance());
		student.getPerson().setOccupation(student);
		return student;
	}

	public static final Student generateStudentInstance() {
		Student student = new Student();
		student.setName("TestStudent");
		return student;
	}

	public static final Subject generateSubjectInstanceWithDepartmentCoursesTeachers() {
		Subject subject = generateSubjectInstanceWithDepartment();
		subject.getCourses().add(TestDataFactoryUtil.generateCourseInstance());
		subject.getTeachers().add(TestDataFactoryUtil.generateTeacherInstance());
		return subject;
	}

	public static final Subject generateSubjectInstanceWithDepartment() {
		Subject subject = generateSubjectInstance();
		subject.setDepartment(generateDepartmentInstance());
		subject.getDepartment().getSubjects().add(subject);
		return subject;
	}

	public static final Subject generateSubjectInstance() {
		Subject subject = new Subject();
		subject.setName("Test Subject");
		return subject;
	}

	public static final Teacher generateTeacherInstanceWithPerson() throws ParseException {
		Teacher teacher = generateTeacherInstance();
		teacher.setPerson(generatePersonInstance());
		teacher.getPerson().setOccupation(teacher);
		return teacher;
	}

	public static final Teacher generateTeacherInstance() {
		Teacher teacher = new Teacher();
		teacher.setName("TestTeacher");
		return teacher;
	}

	public static final University generateUniversityInstanceWithAddressDepartment() {
		University university = generateUniversityInstanceWithDepartment();
		university.setAddress(generateAddressStateInstance());
		university.getAddress().setUniversity(university);
		return university;
	}

	public static final University generateUniversityInstanceWithAddress() {
		University university = generateUniversityInstance();
		university.setAddress(generateAddressStateInstance());
		university.getAddress().setUniversity(university);
		return university;
	}

	public static final University generateUniversityInstanceWithDepartment() {
		University university = generateUniversityInstance();
		Department department = generateDepartmentInstance();
		department.setUniversity(university);
		university.getDepartments().add(department);
		return university;
	}

	public static final University generateUniversityInstance() {
		University university = new University();
		university.setName("TestEdu");
		return university;
	}

	public static final Vehicle generateVehicleInstanceWithPerson() throws ParseException {
		Vehicle vehicle = generateVehicleInstance();
		vehicle.setPerson(generatePersonInstance());
		vehicle.getPerson().setVehicle(vehicle);
		return vehicle;
	}

	public static final Vehicle generateVehicleInstance() {
		Vehicle vehicle = new Vehicle();
		vehicle.setMake("Subaru");
		vehicle.setModel("Impreza WRX");
		vehicle.setYear(Long.valueOf(2004));
		vehicle.setLicensePlate("XNX-5427");
		vehicle.setVin(UUID.randomUUID().toString());
		return vehicle;
	}

}
