package io.github.nosequel.warps.warp

import org.bukkit.Location

class Warp(public val id: String, public var x: Double, public var y: Double, public var z: Double, public var world: String) {
    fun setLocation(location: Location) {
        this.x = location.x
        this.y = location.y
        this.z = location.z
        this.world = location.world.name
    }
}