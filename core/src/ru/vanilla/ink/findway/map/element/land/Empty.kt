package com.vanilla.finderway.map.element.land

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import ru.vanilla.ink.findway.map.element.Cell

/**
 * Created by hedgehog on 05.07.17.
 */
class Empty(texture: Texture) : Cell(texture, Color(1f, 1f, 1f, 1f)){
    override fun blocked(cell: Cell): Boolean {
        return false
    }

    override fun update(map: Array<Array<Cell>>, x: Int, y: Int, texture: Texture) {

    }

}