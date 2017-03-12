package org.aon.rest.enrollment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aon.rest.enrollment.database.DatabaseClass;
import org.aon.rest.enrollment.model.StudentProfileModel;

public class StudentProfileService {

	private Map<Integer, StudentProfileModel>  studentList = DatabaseClass.getStudentProfile();
	
	
	public List<StudentProfileModel> getAllStudentProfile() {
		return new ArrayList<>(studentList.values());
	}

	public StudentProfileModel addStudentProfile(StudentProfileModel model) {
		model.setStudentNumber(studentList.size() + 1);
		studentList.put(model.getStudentNumber(), model);
		return model;
	}
	
	public boolean isEnrolled(int studentNumber){
		
		
		return studentList.get(studentNumber) != null ? true : false;
	}
	

}
