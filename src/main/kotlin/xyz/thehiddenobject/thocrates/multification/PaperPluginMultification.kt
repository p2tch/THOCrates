package xyz.thehiddenobject.thocrates.multification

import com.eternalcode.multification.adventure.AudienceConverter
import com.eternalcode.multification.paper.PaperMultification
import com.eternalcode.multification.translation.TranslationProvider
import com.google.inject.Inject
import com.google.inject.Singleton
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.ComponentSerializer
import org.bukkit.command.CommandSender
import xyz.thehiddenobject.thocrates.configuration.MessagesConfiguration

@Singleton
class PaperPluginMultification @Inject constructor(
    private val messagesConfiguration: MessagesConfiguration
): PaperMultification<MessagesConfiguration>() {

    override fun translationProvider(): TranslationProvider<MessagesConfiguration?> {
        return TranslationProvider { messagesConfiguration }
    }

    override fun serializer(): ComponentSerializer<Component?, Component?, String?> {
        return MiniMessage.miniMessage()
    }

    override fun audienceConverter(): AudienceConverter<CommandSender?> {
        return AudienceConverter { commandSender -> commandSender }
    }
}