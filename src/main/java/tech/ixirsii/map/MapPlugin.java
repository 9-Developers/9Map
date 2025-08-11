package tech.ixirsii.map;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import tech.ixirsii.map.webserver.WebServer;

/**
 * 9Map plugin.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public class MapPlugin extends JavaPlugin {
    /**
     * Server logger.
     */
    private final Logger logger = getLogger();
    /**
     * Plugin web server.
     */
    private final WebServer webServer = new WebServer(logger);

    @Override
    public void onDisable() {
        logger.log(Level.INFO, "Stopping 9Map");

        webServer.stop();

        logger.log(Level.INFO, "9Map disabled");
    }

    @Override
    public void onEnable() {
        logger.log(Level.INFO, "Starting 9Map");

        webServer.start();

        logger.log(Level.INFO, "9Map enabled");
    }
}
