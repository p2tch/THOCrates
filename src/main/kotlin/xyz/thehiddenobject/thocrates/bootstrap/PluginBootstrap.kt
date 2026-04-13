package xyz.thehiddenobject.thocrates.bootstrap

import com.google.inject.Guice
import com.google.inject.Injector
import dev.rollczi.litecommands.LiteCommands
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import xyz.thehiddenobject.thocrates.extension.slf4j
import xyz.thehiddenobject.thocrates.module.ConfigurationModule
import xyz.thehiddenobject.thocrates.plugin.command.CrateCommand

class PluginBootstrap : JavaPlugin() {
    private lateinit var litecommands: LiteCommands<CommandSender>
    private lateinit var injector:     Injector
    private val logger by slf4j()

    override fun onEnable() {
        try {
            logger.info("Initializing THOCrates plugin...")

            injector = Guice.createInjector(
                ConfigurationModule(dataFolder)
            )

            this.litecommands = LiteBukkitFactory.builder("thocrates", this)
                .commands(
                    injector.getInstance(CrateCommand::class.java)
                ).build()
        } catch (e: Exception) {
            logger.error("Failed to initialize THOCrates plugin!", e)
        }
    }

    override fun onDisable() {
        litecommands.unregister()

        logger.info("THOCrates disabled!")
    }
}
