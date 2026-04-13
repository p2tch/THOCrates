package xyz.thehiddenobject.thocrates.plugin.command

import com.eternalcode.multification.notice.Notice
import com.google.inject.Inject
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.ExecuteDefault
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.entity.Player
import xyz.thehiddenobject.thocrates.BuildParameters
import xyz.thehiddenobject.thocrates.multification.PaperPluginMultification
import xyz.thehiddenobject.thocrates.plugin.command.const.CommandPermissions

@Command(name = "crate", aliases = ["crates"])
@Permission(CommandPermissions.CRATE_COMMAND_PERMISSION)
class CrateCommand @Inject constructor(
    private val multification: PaperPluginMultification,
) {
    @ExecuteDefault
    fun default(@Context player: Player) {
        val notice: Notice = Notice.builder()
            .chat(
                "",
                "<dark_gray>» <gray>You are currently using <#E5A050><bold>THOCrates</bold> ${BuildParameters.VERSION}",
                "<gray>Use <#E5A050><italic>/crate help</italic> <gray>to see the list of available commands.",
                ""
            )
            .build()

        multification.create()
            .player(player.uniqueId)
            .notice(notice)
            .send()
    }
}