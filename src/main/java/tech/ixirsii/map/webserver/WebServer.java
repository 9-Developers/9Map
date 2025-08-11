package tech.ixirsii.map.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.ResourceFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Jetty web server configuration.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public class WebServer {
    /**
     * Web server port.
     */
    private static final int PORT = 9999;
    /**
     * Web server thread pool name.
     */
    private static final String THREAD_POOL_NAME = "9Map";

    /**
     * Server logger.
     */
    private final Logger logger;
    /**
     * Jetty web server.
     */
    private final Server server;

    /**
     * Constructor.
     *
     * @param logger Server logger.
     */
    public WebServer(final Logger logger) {
        this.logger = logger;
        final QueuedThreadPool threadPool = new QueuedThreadPool();
        server = new Server(threadPool);
        final ServerConnector connector = new ServerConnector(server);
        final ResourceHandler handler = new ResourceHandler();

        connector.setPort(PORT);
        handler.setBaseResource(ResourceFactory.of(handler).newResource("/site/"));
        handler.setDirAllowed(false);
        handler.setWelcomeFiles(List.of("index.html"));
        handler.setAcceptRanges(true);
        server.addConnector(connector);
        server.setHandler(handler);
        threadPool.setName(THREAD_POOL_NAME);
    }

    /**
     * Start the web server.
     */
    public void start() {
        try {
            server.start();
        } catch (final Exception e) {
            logger.log(Level.SEVERE, "Failed to start web server", e);
        }
    }

    /**
     * Stop the web server.
     */
    public void stop() {
        try {
            server.stop();
        } catch (final Exception e) {
            logger.log(Level.SEVERE, "Failed to stop web server", e);
        } finally {
            server.destroy();
        }
    }
}
