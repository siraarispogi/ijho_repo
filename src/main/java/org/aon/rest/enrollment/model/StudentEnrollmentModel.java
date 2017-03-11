package org.aon.rest.enrollment.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentEnrollmentModel {

	private List<SubjectCatalogModel> enrolledSubject;

	public StudentEnrollmentModel() {
	}

	public List<SubjectCatalogModel> getEnrolledSubject() {
		return enrolledSubject;
	}

	public void setEnrolledSubject(List<SubjectCatalogModel> enrolledSubject) {
		this.enrolledSubject = enrolledSubject;
	}

}
