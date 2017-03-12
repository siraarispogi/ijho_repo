package org.aon.rest.enrollment.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.aon.rest.enrollment.model.ErrorMessageModel;
import org.aon.rest.enrollment.model.StudentEnrollmentModel;
import org.aon.rest.enrollment.service.StudentEnrollmentService;
import org.aon.rest.enrollment.service.StudentProfileService;
import org.aon.rest.enrollment.validator.StudentEnrollmentValidator;

@Path("/enrollment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class StudentEnrollmentResource {

	private StudentEnrollmentService studentEnrollmentService = new StudentEnrollmentService();
	private StudentEnrollmentValidator validator = new StudentEnrollmentValidator();

	@GET
	@Path("/{studentNumber}")
	public StudentEnrollmentModel getEnrolledSubject(@PathParam("studentNumber") int studentNumber) {
		validator.checkStudentEnrolledID(studentNumber);
		validator.checkEnrolledSubject(studentNumber);
		return studentEnrollmentService.getEnrolledSubject(studentNumber);
	}


	@POST
	@Path("/{studentNumber}")
	public Response enrollSubject(@PathParam("studentNumber") int studentNumber,
													@QueryParam("subjectcode") int subjectCode) {
		validator.checkStudentEnrolledID(studentNumber);
		validator.checkSubjectInDatabase(subjectCode);
		validator.checkSubjectInEnrolledList(studentNumber,subjectCode, "add");
		StudentEnrollmentModel addModel = studentEnrollmentService.enrollSubject(studentNumber, subjectCode);
		return Response.status(Status.CREATED).entity(addModel).build();
	}

	@DELETE
	@Path("/{studentNumber}")
	public Response removeSubject(@PathParam("studentNumber") int studentNumber,
													@QueryParam("subjectcode") int subjectCode) {
		validator.checkStudentEnrolledID(studentNumber);
		validator.checkEnrolledSubject(studentNumber);
		validator.checkSubjectInDatabase(subjectCode);
		validator.checkSubjectInEnrolledList(studentNumber,subjectCode, "remove");
		StudentEnrollmentModel delModel = studentEnrollmentService.removeSubject(studentNumber, subjectCode);
		return Response.status(Status.ACCEPTED).entity(delModel).build() ;
	}
	
}