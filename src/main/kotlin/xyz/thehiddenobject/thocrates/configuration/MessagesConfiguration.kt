package xyz.thehiddenobject.thocrates.configuration

import com.eternalcode.multification.notice.Notice
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.Header

@Header(
    "Messages configuration file"
)
class MessagesConfiguration: OkaeriConfig() {
    var reloaded: Notice = Notice.builder()
        .chat("<dark_gray>» <gray>Reloaded in <#E5A050>{TIME} ms")
        .sound("minecraft:entity.player.levelup")
        .build()
}