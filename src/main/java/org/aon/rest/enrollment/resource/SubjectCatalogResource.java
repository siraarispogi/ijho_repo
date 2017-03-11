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

import org.aon.rest.enrollment.model.SubjectCatalogModel;
import org.aon.rest.enrollment.service.SubjectCatalogService;

@Path("/subject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
public class SubjectCatalogResource {
	
	private SubjectCatalogService subjectCatalogService = new SubjectCatalogService();
	
	@GET
	public List<SubjectCatalogModel> getAllSubjectInCatalog(@QueryParam("status") String status){
		if(status!=null){
			return subjectCatalogService.getSubjectbyStatus(status);
		}else{
			return subjectCatalogService.getAllSubjectInCatalog();
		}
	}
	
	@POST
	public void addSubjectInCatalog(SubjectCatalogModel model){
		for(SubjectCatalogModel mod : model.getListOfSubject()){
			subjectCatalogService.addSubjectInCatalog(mod);
		}
	}
	
	@PUT
	@Path("/{subjectCode}")
	public SubjectCatalogModel updateSubjectInCatalog(@PathParam("subjectCode") int subjectCode, 
														SubjectCatalogModel model){
		model.setSubjectCode(subjectCode);
		return subjectCatalogService.updateSubjectInCatalog(subjectCode, model);
	}
	
}
