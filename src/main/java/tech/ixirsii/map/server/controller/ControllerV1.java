package tech.ixirsii.map.server.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import tech.ixirsii.map.server.data.Message;

/**
 * REST controller.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
@Path("/api/v1")
public class ControllerV1 {
    /**
     * Hello world endpoint.
     *
     * @return Hello world!
     */
    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Message hello() {
        return new Message("Hello world!");
    }
}
