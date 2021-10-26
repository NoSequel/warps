package io.github.nosequel.warps.command

import io.github.nosequel.command.annotation.Command
import io.github.nosequel.command.annotation.Help
import io.github.nosequel.command.annotation.Subcommand
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor
import io.github.nosequel.command.exception.ConditionFailedException
import io.github.nosequel.command.executor.CommandExecutor
import io.github.nosequel.warps.warp.Warp
import io.github.nosequel.warps.warp.WarpHandler
import org.bukkit.Bukkit
import org.bukkit.Location

class WarpCommand(private val handler: WarpHandler) {

    @Command(label = "warp")
    fun warp(executor: BukkitCommandExecutor, warp: Warp) {
        executor.player.teleport(
            Location(
                Bukkit.getWorld(warp.world),
                warp.x, warp.y, warp.z
            )
        )
    }

    @Help
    @Command(label = "warps", permission = "warps.admin")
    fun help(executor: CommandExecutor) {
    }

    @Subcommand(label = "create", parentLabel = "warps", permission = "warps.admin.create")
    fun create(executor: BukkitCommandExecutor, name: String) {
        if (this.handler.findWarp(name) != null) {
            throw ConditionFailedException("A warp with the name $name already exists.")
        }

        this.handler.warps.add(Warp(name, 0.0, 0.0, 0.0, "world").also {
            if (executor.isUser) {
                it.setLocation(executor.player.location)
            }
        })
    }

    @Subcommand(label = "delete", parentLabel = "warps", permission = "warps.admin.delete")
    fun delete(executor: CommandExecutor, warp: Warp) {
        this.handler.warps.remove(warp)
    }

    @Subcommand(label = "location", parentLabel = "warps", permission = "warps.admin.location")
    fun location(executor: BukkitCommandExecutor, warp: Warp) {
        warp.setLocation(executor.player.location)
    }
}