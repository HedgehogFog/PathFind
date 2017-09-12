package com.vanilla.finderway.map.element.land

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import ru.vanilla.ink.findway.map.element.Cell

/**
 * Created by hedgehog on 07.07.17.
 */
class Exit(texture: Texture) : Cell(texture, Color(0.5f, 0.5f, 0.5f, 1f)) {
    override fun blocked(cell: Cell): Boolean {
        return false
    }

    override fun update(map: Array<Array<Cell>>, x: Int, y: Int, texture: Texture) {
    }
}