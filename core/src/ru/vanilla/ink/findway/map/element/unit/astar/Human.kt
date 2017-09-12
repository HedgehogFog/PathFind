package com.vanilla.finderway.map.element.unit

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.IntArray
import com.vanilla.finderway.map.element.land.Dirt
import ru.vanilla.ink.findway.astar.AStarPathFinder
import ru.vanilla.ink.findway.info.AppConstants
import ru.vanilla.ink.findway.info.Data
import ru.vanilla.ink.findway.map.element.Cell

/**
 * Created by hedgehog on 08.07.17.
 */
class Human(texture: Texture) : Cell(texture, Color(0.3f, 0f, 0f, 1f)) {

    val pathFind = AStarPathFinder(AppConstants.APP_WIDTH.toInt(), AppConstants.APP_HEIGHT.toInt())
    var path = IntArray()
    var update_time = AppConstants.UPDATE_TIME

    override fun blocked(cell: Cell): Boolean {
        return false
    }

    override fun update(map: Array<Array<Cell>>, x: Int, y: Int, texture: Texture) {
        update_time -= Gdx.graphics.deltaTime
        if (update_time <= 0) {
            update_time += AppConstants.UPDATE_TIME

            for (exit in Data.exitList) {
                path = pathFind.getPath(x, y, exit.x.toInt(), exit.y.toInt(), this)
                if (path.size != 0)
                    break
            }

            if (path.size != 0) {
//                Gdx.app.log("Human", "Path not found")
//                var i = 0
                val n = path.size

                val targetX = path.get(n - 2)
                val targetY = path.get(n - 1)

                map[targetX][targetY] = this
                map[x][y] = Dirt(texture)
            }
        }
    }
}
