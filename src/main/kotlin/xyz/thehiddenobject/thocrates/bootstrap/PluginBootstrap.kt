package xyz.thehiddenobject.thocrates.bootstrap

import org.bukkit.plugin.java.JavaPlugin
import xyz.thehiddenobject.thocrates.extension.slf4j

class PluginBootstrap : JavaPlugin() {
    private val logger by slf4j()

    override fun onEnable() {
        try {
            logger.info("Initializing THOCrates plugin...")
        } catch (e: Exception) {
            logger.error("Failed to initialize THOCrates plugin!", e)
        }
    }

    override fun onDisable() {
        logger.info("THOCrates disabled!")
    }
}
