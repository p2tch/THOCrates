package xyz.thehiddenobject.thocrates.module

import com.eternalcode.multification.notice.resolver.NoticeResolverDefaults
import com.eternalcode.multification.okaeri.MultificationSerdesPack
import com.google.inject.AbstractModule
import eu.okaeri.configs.ConfigManager
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.OkaeriConfigOptions
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer
import xyz.thehiddenobject.thocrates.configuration.CratesConfiguration
import xyz.thehiddenobject.thocrates.configuration.GeneralConfiguration
import xyz.thehiddenobject.thocrates.configuration.MessagesConfiguration
import java.io.File

class ConfigurationModule(
    private val dataFolder: File,
): AbstractModule() {
    override fun configure() {
        val noticeRegistry = NoticeResolverDefaults.createRegistry()

        bind(MessagesConfiguration::class.java).toInstance(
            loadConfig(MessagesConfiguration::class.java, "messages.yml") { opt ->
                opt.serdes(MultificationSerdesPack(noticeRegistry))
            }
        )

        bind(GeneralConfiguration::class.java).toInstance(
            loadConfig(GeneralConfiguration::class.java, "configuration.yml")
        )

        bind(CratesConfiguration::class.java).toInstance(
            loadConfig(CratesConfiguration::class.java, "crates.yml")
        )
    }

    private fun <T : OkaeriConfig> loadConfig(
        clazz: Class<T>,
        fileName: String,
        configure: (OkaeriConfigOptions) -> Unit = {}
    ): T {
        return ConfigManager.create(clazz) {
            it.configure { opt ->
                opt.configurer(YamlBukkitConfigurer())
                configure(opt)
                opt.bindFile(File(dataFolder, fileName))
            }
            it.saveDefaults()
            it.load(true)
        }
    }
}