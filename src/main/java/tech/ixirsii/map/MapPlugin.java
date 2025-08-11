package tech.ixirsii.map;

import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 9Map plugin.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public class MapPlugin extends JavaPlugin {

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "9Map disabled");
    }

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "9Map enabled");
    }
}
