package ru.vanilla.ink.findway.map.element

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.vanilla.ink.findway.info.AppConstants

/**
 * Created by hedgehog on 03.08.17.
 */
abstract class Cell(texture: Texture, private var color: Color) {

    private var sprite = Sprite(texture)

    init {
        sprite.color = color
        sprite.setSize(1f, 1f)
    }

    abstract fun update(map: Array<Array<Cell>>, x: Int, y: Int, texture: Texture)

    fun setColor(color: Color) {
        this.color = color
        sprite.color = color
    }

    fun draw(batch: SpriteBatch, x: Int, y: Int) {
        sprite.setPosition(x.toFloat() - (AppConstants.FIELD_SIZE / 2).toFloat() - sprite.width / 2,
                y.toFloat() - (AppConstants.FIELD_SIZE / 2).toFloat() - sprite.height / 2)
        sprite.draw(batch)
    }

    abstract fun blocked(cell: Cell): Boolean

}