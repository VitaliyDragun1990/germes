package org.itsimulator.germes.app.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.itsimulator.germes.app.infra.exception.flow.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jersey exception handler that catches validation errors.
 * 
 * @author Vitaly Dragun
 *
 */
@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionHandler.class);

	@Override
	public Response toResponse(ValidationException exception) {
		LOGGER.error(exception.getMessage(), exception);
		
		return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}

}
