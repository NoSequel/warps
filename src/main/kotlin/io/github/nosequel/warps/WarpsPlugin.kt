package io.github.nosequel.warps

import io.github.nosequel.command.bukkit.BukkitCommandHandler
import io.github.nosequel.config.json.JsonConfigurationFile
import io.github.nosequel.warps.command.WarpCommand
import io.github.nosequel.warps.command.WarpTypeAdapter
import io.github.nosequel.warps.warp.Warp
import io.github.nosequel.warps.warp.WarpHandler
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class WarpsPlugin : JavaPlugin() {

    override fun onEnable() {
        val handler = WarpHandler(
            JsonConfigurationFile(
                File(
                    this.dataFolder,
                    "warps.json"
                )
            )
        )

        BukkitCommandHandler("warps").also {
            it.registerTypeAdapter(Warp::class.java, WarpTypeAdapter(handler))
            it.registerCommand(WarpCommand(handler))
        }
    }
}