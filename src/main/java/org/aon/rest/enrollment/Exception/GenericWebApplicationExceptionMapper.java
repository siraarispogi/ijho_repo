package org.aon.rest.enrollment.Exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.aon.rest.enrollment.model.ErrorMessageModel;

@Provider
public class GenericWebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException>  {

	@Override
	public Response toResponse(WebApplicationException exception) {
		ErrorMessageModel model = new ErrorMessageModel(exception.getMessage());
		int statusCode = exception.getResponse().getStatus();
		return Response.status(statusCode)
				.type(MediaType.APPLICATION_JSON)
				.entity(model)
				.build();
	}

}
