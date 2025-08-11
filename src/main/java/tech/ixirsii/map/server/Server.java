package tech.ixirsii.map.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Plugin server.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public class Server {
    /**
     * Server base URI.
     */
    private static final String BASE_URI = "https://127.0.0.1:9999";

    /**
     * Grizzly HTTP server.
     */
    @NonNull
    private final HttpServer httpServer;
    /**
     * Plugin logger.
     */
    @NonNull
    private final Logger logger;

    /**
     * Constructor.
     *
     * @param logger Plugin logger.
     * @param mapper Jackson object mapper.
     */
    public Server(@NonNull final Logger logger, @NonNull final ObjectMapper mapper) {
        this.logger = logger;

        final JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(mapper);

        final ResourceConfig resourceConfig = new ResourceConfig()
                .packages("tech.ixirsii.map.server.controller")
                .register(provider);
        final HttpHandler httpHandler = new CLStaticHttpHandler(getClass().getClassLoader(), "/site/");

        httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
        httpServer.getServerConfiguration().addHttpHandler(httpHandler, "/");
    }

    /**
     * Start the web server.
     */
    public void start() {
        try {
            httpServer.start();
        } catch (final IOException e) {
            logger.log(Level.SEVERE, "Failed to start web server", e);
        }
    }

    /**
     * Stop the web server.
     */
    public void stop() {
        httpServer.shutdownNow();
    }
}
