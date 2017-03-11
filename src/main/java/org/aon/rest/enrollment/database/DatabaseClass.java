package org.aon.rest.enrollment.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aon.rest.enrollment.model.StudentEnrollmentModel;
import org.aon.rest.enrollment.model.StudentProfileModel;
import org.aon.rest.enrollment.model.SubjectCatalogModel;

public class DatabaseClass {

	private static Map<Integer, StudentProfileModel> studentList = new HashMap<>();
	
	public static Map<Integer, StudentProfileModel> getStudentProfile() {
		return studentList;
	}
	
	private static Map<Integer, SubjectCatalogModel> subjectList = new HashMap<>();

	public static Map<Integer, SubjectCatalogModel> getSubjectList() {
		return subjectList;
	}

	private static Map<Integer, List<SubjectCatalogModel>> enrolledSubjectList = new HashMap<>();
	
	public static Map<Integer, List<SubjectCatalogModel>> getEnrolledSubject() {
		return enrolledSubjectList;
	}
	
	private static List<Integer> subjectEnrolledByStudent = new ArrayList<>();
	
	public static List<Integer> getSubjectEnrolledByStudent() {
		return subjectEnrolledByStudent;
	}


}
