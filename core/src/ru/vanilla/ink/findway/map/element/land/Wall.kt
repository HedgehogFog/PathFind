package com.vanilla.finderway.map.element.land

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.vanilla.finderway.map.element.unit.Human
import ru.vanilla.ink.findway.map.element.Cell
import ru.vanilla.ink.findway.map.element.unit.astar.Helicopter

/**
 * Created by hedgehog on 05.07.17.
 */
class Wall(texture: Texture) : Cell(texture, Color(1f, 1f, 1f, 1f)) {
    override fun blocked(cell: Cell): Boolean {
        if (cell is Human)
            return true
        return false
    }

    override fun update(map: Array<Array<Cell>>, x: Int, y: Int, texture: Texture) {
    }
}
