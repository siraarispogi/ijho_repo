package org.aon.rest.enrollment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aon.rest.enrollment.database.DatabaseClass;
import org.aon.rest.enrollment.model.StudentEnrollmentModel;
import org.aon.rest.enrollment.model.SubjectCatalogModel;

public class StudentEnrollmentService {

	//getting the value from subject catalog service
	private SubjectCatalogService subjectService = new SubjectCatalogService();
	
	//storage for student enrolled subject
	private  Map<Integer, List<SubjectCatalogModel>> enrolledSubject = DatabaseClass.getEnrolledSubject();
	
//	//initialization
//	public StudentEnrollmentService() {
//		List<SubjectCatalogModel> model = new ArrayList<>();
//		SubjectCatalogModel math = new SubjectCatalogModel(1, "Math", "Math is good", "Active");
//		SubjectCatalogModel science = new SubjectCatalogModel(2, "Science", "Anatomy", "Inactive");
//		model.add(math);
//		model.add(science);
//		enrolledSubject.put(1, model);
//		List<SubjectCatalogModel> model1 = new ArrayList<>();
//		SubjectCatalogModel math1 = new SubjectCatalogModel(3, "Hekasi", "Sibika at Kultura", "Active");
//		SubjectCatalogModel science1 = new SubjectCatalogModel(4, "Java", "Java", "Inactive");
//		model1.add(math1);
//		model1.add(science1);
//		enrolledSubject.put(2, model1);
//	}
	
	public StudentEnrollmentModel getEnrolledSubject(int studentNumber) {
		StudentEnrollmentModel model = new StudentEnrollmentModel();
		model.setEnrolledSubject(enrolledSubject.get(studentNumber));
		return model;
	}

	public StudentEnrollmentModel enrollSubject(int studentNumber, int subjectCode) {
		List<SubjectCatalogModel> existingSubject = enrolledSubject.get(studentNumber);
		
		if(existingSubject==null){
			existingSubject = new ArrayList<SubjectCatalogModel>();
		}	
		
		existingSubject.add(subjectService.getSubjectFromList(subjectCode));
		
		enrolledSubject.put(studentNumber, existingSubject);
		
		StudentEnrollmentModel model = new StudentEnrollmentModel();
		model.setEnrolledSubject(existingSubject);
		
		//add the subject code to the enrolled subject
		//use so that staff cannot update subject that is enrolled
		subjectService.addSubjectEnrolledByStudent(subjectCode);
		
		return model;
	}

	public  StudentEnrollmentModel removeSubject(int studentNumber, int subjectCode){
		List<SubjectCatalogModel> existingSubject = enrolledSubject.get(studentNumber);
		
		if(existingSubject==null){
			return null;
		}
		
		int indexOfSubject = -1;
		for(int x=0; x<existingSubject.size(); x++){
			if(existingSubject.get(x).getSubjectCode()==subjectCode){
				indexOfSubject = x;
				break;
			}
		}
		if(indexOfSubject>=0){
			existingSubject.remove(indexOfSubject);
		}
		StudentEnrollmentModel model = new StudentEnrollmentModel();
		model.setEnrolledSubject(existingSubject);
		return model;
	}

	
}
