package com.wandercosta.bookstore.exception;

import java.util.Collections;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Class to catch exceptions and return user-friendly responses.
 *
 * @author Wander Costa (www.wandercosta.com)
 */
@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        if (ex instanceof NullPointerException) {
            return getResponse((NullPointerException) ex);
        } else if (ex instanceof IllegalArgumentException) {
            return getResponse((IllegalArgumentException) ex);
        } else {
            return getResponse(ex);
        }
    }

    private Response getResponse(NullPointerException ex) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Collections.singletonMap("error", ex.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response getResponse(IllegalArgumentException ex) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Collections.singletonMap("error", ex.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response getResponse(Exception ex) {
        return Response.serverError()
                .entity(Collections.singletonMap("error", "Unknown error: " + ex.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
