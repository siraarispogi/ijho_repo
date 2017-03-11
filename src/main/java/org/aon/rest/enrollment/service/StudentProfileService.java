package org.aon.rest.enrollment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aon.rest.enrollment.database.DatabaseClass;
import org.aon.rest.enrollment.model.StudentProfileModel;

public class StudentProfileService {

	private Map<Integer, StudentProfileModel>  studentList = DatabaseClass.getStudentProfile();
	
//	public StudentProfileService() {
//		studentList.put(1,new StudentProfileModel("Aris", "Nazareno", "Male", 5,1));
//		studentList.put(2,new StudentProfileModel("Lara", "Madamba", "Female", 3,2));
//		studentList.put(3,new StudentProfileModel("Theo", "Nazareno", "Male", 4,3));
//	}
	
	public List<StudentProfileModel> getAllStudentProfile() {
		return new ArrayList<>(studentList.values());
	}

	public StudentProfileModel addStudentProfile(StudentProfileModel model) {
		model.setStudentNumber(studentList.size() + 1);
		studentList.put(model.getStudentNumber(), model);
		return model;
	}
	

}
