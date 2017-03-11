package org.aon.rest.enrollment.model;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class SubjectCatalogModel {
	
	private int subjectCode;
	private String subjectName;
	private String description;
	private String status;
	
	@XmlTransient
	private List<SubjectCatalogModel> listOfSubject;
	
	public List<SubjectCatalogModel> getListOfSubject() {
		return listOfSubject;
	}

	public void setListOfSubject(List<SubjectCatalogModel> listOfSubject) {
		this.listOfSubject = listOfSubject;
	}

	public SubjectCatalogModel() {
	}
	
//	public SubjectCatalogModel(int subjetCode, String subjectname, String description, String status) {
//		this.subjectCode = subjetCode;
//		this.subjectName = subjectname;
//		this.description = description;
//		this.status = status;
//	}
	
	public int getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
