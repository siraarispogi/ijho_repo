package org.aon.rest.enrollment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aon.rest.enrollment.database.DatabaseClass;
import org.aon.rest.enrollment.model.SubjectCatalogModel;

public class SubjectCatalogService {
	
	private Map<Integer, SubjectCatalogModel> subjectList = DatabaseClass.getSubjectList();
	
	private List<Integer> subjectEnrolledByStudent = DatabaseClass.getSubjectEnrolledByStudent();
	
//	public SubjectCatalogService() {
//		subjectList.put(1, new SubjectCatalogModel(1, "Math", "Math is good", "Active"));
//		subjectList.put(2, new SubjectCatalogModel(2, "Science", "Anatomy", "Inactive"));
//		subjectList.put(3, new SubjectCatalogModel(3, "Hekasi", "Sibika at Kultura", "Active"));
//	}

	public List<SubjectCatalogModel> getAllSubjectInCatalog() {
		return new ArrayList<SubjectCatalogModel>(subjectList.values());
	}

	public List<SubjectCatalogModel> getSubjectbyStatus(String status) {
		List<SubjectCatalogModel> list = new ArrayList<>();
		for(SubjectCatalogModel model : subjectList.values()){
			if(model.getStatus().equalsIgnoreCase(status)){
				list.add(model);
			}
		}
		return list;
	}

	public SubjectCatalogModel addSubjectInCatalog(SubjectCatalogModel model) {
		model.setSubjectCode(subjectList.size()+1);
		subjectList.put(model.getSubjectCode(), model);
		return model;
	}

	public SubjectCatalogModel updateSubjectInCatalog(int subjectCode, SubjectCatalogModel model) {
		if(subjectEnrolledByStudent.contains(subjectCode)){
			return null;
		}
		subjectList.put(subjectCode, model);
		return model;
	}
	
	
	public SubjectCatalogModel getSubjectFromList(int subjectCode){
		return subjectList.get(subjectCode);
	}

	public void addSubjectEnrolledByStudent(int subjectCode){
		subjectEnrolledByStudent.add(subjectCode);
	}
	
}
