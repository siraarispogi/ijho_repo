package org.aon.rest.enrollment.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.aon.rest.enrollment.model.StudentProfileModel;
import org.aon.rest.enrollment.service.StudentProfileService;

@Path("/studentprofile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
public class StudentProfileResource {
	
	private StudentProfileService studentProfileService  = new StudentProfileService();
	
	@GET
	public List<StudentProfileModel> getAllStudentProfile(){
		return studentProfileService.getAllStudentProfile();
	}
	
	@POST
	public StudentProfileModel addStudentProfile(StudentProfileModel model){
		return studentProfileService.addStudentProfile(model);
	}
	
}
