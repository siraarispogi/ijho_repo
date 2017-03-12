package org.aon.rest.enrollment.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.aon.rest.enrollment.model.SubjectCatalogModel;
import org.aon.rest.enrollment.service.SubjectCatalogService;
import org.aon.rest.enrollment.validator.SubjectCatalogValidator;

@Path("/subject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
public class SubjectCatalogResource {
	
	private SubjectCatalogService subjectCatalogService = new SubjectCatalogService();
	private SubjectCatalogValidator validator = new SubjectCatalogValidator();
	
	@GET
	public List<SubjectCatalogModel> getAllSubjectInCatalog(@QueryParam("status") String status){
		validator.checkSubjectInCatalog(status);
		if(status!=null){
			return subjectCatalogService.getSubjectbyStatus(status);
		}else{
			return subjectCatalogService.getAllSubjectInCatalog();
		}
	}
	
	@POST
	public Response addSubjectInCatalog(SubjectCatalogModel model){
		validator.checkInput(model);
		for(SubjectCatalogModel mod : model.getListOfSubject()){
			subjectCatalogService.addSubjectInCatalog(mod);
		}
		
		return Response.status(Status.CREATED).type(MediaType.APPLICATION_JSON).build();
	}	
	
	@PUT
	@Path("/{subjectCode}")
	public Response updateSubjectInCatalog(@PathParam("subjectCode") int subjectCode, 
														SubjectCatalogModel model){
		validator.checkSubjectIfEnrolled(subjectCode);
		model.setSubjectCode(subjectCode);
		SubjectCatalogModel updatedModel = subjectCatalogService.updateSubjectInCatalog(subjectCode, model);
		return Response.status(Status.ACCEPTED)
				.type(MediaType.APPLICATION_JSON)
				.entity(updatedModel)
				.build();
	}
	
}
