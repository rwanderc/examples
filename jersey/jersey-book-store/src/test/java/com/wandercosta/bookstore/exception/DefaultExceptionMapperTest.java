package com.wandercosta.bookstore.exception;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;

/**
 * Class to test exception mapping.
 *
 * @author Wander Costa (www.wandercosta.com)
 */
public class DefaultExceptionMapperTest {

    private final DefaultExceptionMapper mapper = new DefaultExceptionMapper();

    @Test
    public void shouldHandleNullPointerException() {
        Exception ex = new NullPointerException("mocked exception");
        Response resp = mapper.toResponse(ex);

        assertEquals(Response.Status.NOT_FOUND, resp.getStatusInfo());
        assertEquals(ex.getMessage(), ((Map) resp.getEntity()).get("error"));
        assertEquals(MediaType.APPLICATION_JSON_TYPE, resp.getMediaType());
    }

    @Test
    public void shouldHandleIllegalArgumentException() {
        Exception ex = new IllegalArgumentException("mocked exception");
        Response resp = mapper.toResponse(ex);

        assertEquals(Response.Status.BAD_REQUEST, resp.getStatusInfo());
        assertEquals(ex.getMessage(), ((Map) resp.getEntity()).get("error"));
        assertEquals(MediaType.APPLICATION_JSON_TYPE, resp.getMediaType());
    }

    @Test
    public void shouldHandleOtherException() {
        Exception ex = new Exception("mocked exception");
        Response resp = mapper.toResponse(ex);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR, resp.getStatusInfo());
        assertEquals("Unknown error: " + ex.getMessage(), ((Map) resp.getEntity()).get("error"));
        assertEquals(MediaType.APPLICATION_JSON_TYPE, resp.getMediaType());
    }

}
