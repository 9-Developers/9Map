package tech.ixirsii.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jaxrs.Jaxrs2TypesModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.bukkit.plugin.java.JavaPlugin;
import tech.ixirsii.map.server.Server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 9Map plugin.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public class MapPlugin extends JavaPlugin {
    /**
     * Plugin logger.
     */
    private final Logger logger;
    /**
     * Object mapper for (de)serialization.
     */
    private final ObjectMapper mapper;
    /**
     * Plugin REST server.
     */
    private final Server server;

    /**
     * Constructor.
     */
    public MapPlugin() {
        logger = getLogger();
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .addModule(new Jaxrs2TypesModule())
                .addModule(new Jdk8Module())
                .addModule(new ParameterNamesModule())
                .build();
        server = new Server(logger, mapper);
    }

    @Override
    public void onDisable() {
        logger.log(Level.INFO, "Stopping 9Map");

        server.stop();

        logger.log(Level.INFO, "9Map disabled");
    }

    @Override
    public void onEnable() {
        logger.log(Level.INFO, "Starting 9Map");

        server.start();

        logger.log(Level.INFO, "9Map enabled");
    }
}
