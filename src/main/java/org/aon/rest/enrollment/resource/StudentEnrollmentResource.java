package org.aon.rest.enrollment.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.aon.rest.enrollment.model.StudentEnrollmentModel;
import org.aon.rest.enrollment.service.StudentEnrollmentService;

@Path("/enrollment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class StudentEnrollmentResource {

	private StudentEnrollmentService studentEnrollmentService = new StudentEnrollmentService();

	@GET
	@Path("/{studentNumber}")
	public StudentEnrollmentModel getEnrolledSubject(@PathParam("studentNumber") int studentNumber) {
		return studentEnrollmentService.getEnrolledSubject(studentNumber);
	}

	@POST
	@Path("/{studentNumber}")
	public StudentEnrollmentModel enrollSubject(@PathParam("studentNumber") int studentNumber,
													@QueryParam("subjectcode") int subjectCode) {
		return studentEnrollmentService.enrollSubject(studentNumber, subjectCode);
	}

	@DELETE
	@Path("/{studentNumber}")
	public StudentEnrollmentModel removeSubject(@PathParam("studentNumber") int studentNumber,
													@QueryParam("subjectcode") int subjectCode) {
		return studentEnrollmentService.removeSubject(studentNumber, subjectCode);
	}
}