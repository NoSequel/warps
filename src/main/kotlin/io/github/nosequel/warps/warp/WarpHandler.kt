package io.github.nosequel.warps.warp

import io.github.nosequel.config.Configuration
import io.github.nosequel.config.ConfigurationFile

class WarpHandler(file: ConfigurationFile) : Configuration(file) {

    init {
        this.load()
        this.save()
    }

    val warps = mutableListOf<Warp>()

    fun findWarp(id: String): Warp? {
        return warps.find { it.id.equals(id, true) }
    }
}