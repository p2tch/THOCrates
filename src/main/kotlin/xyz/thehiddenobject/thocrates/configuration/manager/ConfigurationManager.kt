package xyz.thehiddenobject.thocrates.configuration.manager

import com.google.inject.Inject
import com.google.inject.Singleton
import xyz.thehiddenobject.thocrates.configuration.CratesConfiguration
import xyz.thehiddenobject.thocrates.configuration.GeneralConfiguration
import xyz.thehiddenobject.thocrates.configuration.MessagesConfiguration

@Singleton
class ConfigurationManager @Inject constructor(
    cratesConfiguration: CratesConfiguration,
    generalConfiguration: GeneralConfiguration,
    messagesConfiguration: MessagesConfiguration
) {
    private val configs = mapOf(
        ConfigurationFiles.CRATES to cratesConfiguration,
        ConfigurationFiles.GENERAL to generalConfiguration,
        ConfigurationFiles.MESSAGES to messagesConfiguration
    )

    fun reloadAll() {
        configs.values.forEach { it.load(true) }
    }

    fun reload(configurationFile: ConfigurationFiles) {
        configs[configurationFile]?.load(true)
    }
}