package org.aon.rest.enrollment.validator;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.aon.rest.enrollment.model.ErrorMessageModel;
import org.aon.rest.enrollment.model.SubjectCatalogModel;
import org.aon.rest.enrollment.service.StudentEnrollmentService;
import org.aon.rest.enrollment.service.StudentProfileService;
import org.aon.rest.enrollment.service.SubjectCatalogService;

public class StudentEnrollmentValidator {

	private StudentProfileService studentProfileService  = new StudentProfileService();
	private SubjectCatalogService subjectService = new SubjectCatalogService();
	private StudentEnrollmentService studentEnrollmentService = new StudentEnrollmentService();
	
	
	public void checkEnrolledSubject(int studentNumber){
		if(studentEnrollmentService.getEnrolledSubject(studentNumber).getEnrolledSubject()==null ||
				studentEnrollmentService.getEnrolledSubject(studentNumber).getEnrolledSubject().isEmpty()){
			ErrorMessageModel model = new ErrorMessageModel("No Subject Enrolled");
			throw new WebApplicationException(
					Response.status(Status.NOT_FOUND)
					.entity(model)
					.type(MediaType.APPLICATION_JSON)
					.build());
		}
		
	}
	
	public void checkStudentEnrolledID(int studentNumber) {
		if(!studentProfileService.isEnrolled(studentNumber)){
			ErrorMessageModel model = new ErrorMessageModel("Student is not yet enrolled");
			throw new WebApplicationException(
					Response.status(Status.NOT_FOUND)
					.entity(model)
					.type(MediaType.APPLICATION_JSON)
					.build());
		}
	}
	
	public void checkSubjectInDatabase(int subjectCode){
		if(subjectCode<=0){
			ErrorMessageModel model = new ErrorMessageModel("Subject Code is not present");
			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST)
					.entity(model)
					.type(MediaType.APPLICATION_JSON)
					.build());
		}
		
		if(subjectService.getSubjectFromList(subjectCode)==null){
			ErrorMessageModel model = new ErrorMessageModel("Subject is not present in Catalog");
			throw new WebApplicationException(
					Response.status(Status.NOT_FOUND)
					.entity(model)
					.type(MediaType.APPLICATION_JSON)
					.build());
		}
	}
	
	
	public void checkSubjectInEnrolledList(int studentNumber, int subjectCode, String action){
		List<SubjectCatalogModel> existingSubject = 
				studentEnrollmentService.getEnrolledSubject(studentNumber).getEnrolledSubject();
		
		if(existingSubject!=null){
			if(subjectCode<=0){
				ErrorMessageModel model = new ErrorMessageModel("Subject Code is not present");
				throw new WebApplicationException(
						Response.status(Status.BAD_REQUEST)
						.entity(model)
						.type(MediaType.APPLICATION_JSON)
						.build());
			}
			
			boolean result = false;
			for(SubjectCatalogModel mod : existingSubject){
				if(mod.getSubjectCode()==subjectCode){
					result = true;
				}
			}
			
			if(action.equalsIgnoreCase("remove") && !result){
				ErrorMessageModel model = new ErrorMessageModel("Subject Code is not present in Enrolled Subject");
				throw new WebApplicationException(
						Response.status(Status.NOT_FOUND)
						.entity(model)
						.type(MediaType.APPLICATION_JSON)
						.build());
			}else if(action.equalsIgnoreCase("add") && result){
					ErrorMessageModel model = new ErrorMessageModel("Subject Code is already enrolled");
					throw new WebApplicationException(
							Response.status(Status.BAD_REQUEST)
							.entity(model)
							.type(MediaType.APPLICATION_JSON)
							.build());
			}
		}
		
	}
}
