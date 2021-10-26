package io.github.nosequel.warps.command

import io.github.nosequel.command.adapter.TypeAdapter
import io.github.nosequel.command.executor.CommandExecutor
import io.github.nosequel.warps.warp.Warp
import io.github.nosequel.warps.warp.WarpHandler

class WarpTypeAdapter(private val handler: WarpHandler) : TypeAdapter<Warp> {
    override fun convert(executor: CommandExecutor, source: String): Warp {
        return handler.findWarp(source)!!
    }
}