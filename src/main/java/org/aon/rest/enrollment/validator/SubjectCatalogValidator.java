package org.aon.rest.enrollment.validator;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.aon.rest.enrollment.model.ErrorMessageModel;
import org.aon.rest.enrollment.model.SubjectCatalogModel;
import org.aon.rest.enrollment.service.SubjectCatalogService;

public class SubjectCatalogValidator {
	private SubjectCatalogService subjectCatalogService = new SubjectCatalogService();
	
	private List<String> statusList = Arrays.asList("active","inactive");
	
	public void checkInput(SubjectCatalogModel model){
		if(model.getListOfSubject()==null){
			ErrorMessageModel errModel = new ErrorMessageModel("Invalid  format");
			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST)
					.entity(errModel)
					.type(MediaType.APPLICATION_JSON)
					.build());
		}
		
	}
	
	public void checkSubjectInCatalog(String status){
		ErrorMessageModel model = new ErrorMessageModel();
		

		boolean result = false;
		if(status!=null){
			
			if(!statusList.contains(status)){
				if(status.length()==0){
					status = "is empty";
				}else{
					status = status + " is not valid";
				}
				model.setErrorMessage("Status input "+status+". (Status are active and inactive only)");
				throw new WebApplicationException(
						Response.status(Status.BAD_REQUEST)
						.entity(model)
						.type(MediaType.APPLICATION_JSON)
						.build());
			}
			
			if(subjectCatalogService.getSubjectbyStatus(status).size()==0){
				model.setErrorMessage("Catalog for "+status+" is empty");
				result = true;
			}
		}else{
			if(subjectCatalogService.getAllSubjectInCatalog().size()==0){
				model.setErrorMessage("Subject Catalog is empty");
				result = true;
			}
		}
		
		if(result){
			throw new WebApplicationException(
					Response.status(Status.NOT_FOUND)
					.entity(model)
					.type(MediaType.APPLICATION_JSON)
					.build());
		}
	}
	
	public void checkSubjectIfEnrolled(int subjectCode){
		if(subjectCatalogService.checkSubjectInEnrolledBySubject(subjectCode)){
			ErrorMessageModel model = new ErrorMessageModel("Student is enrolled in this subject, cannot update this subject");
			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST)
					.entity(model)
					.type(MediaType.APPLICATION_JSON)
					.build());
		}
	}

}
