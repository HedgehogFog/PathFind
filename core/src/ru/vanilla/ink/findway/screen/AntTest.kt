package ru.vanilla.ink.findway.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.vanilla.finderway.map.element.land.Empty
import com.vanilla.finderway.map.element.land.Exit
import com.vanilla.finderway.map.element.land.Wall
import ru.vanilla.ink.findway.info.AppConstants
import ru.vanilla.ink.findway.info.Data
import ru.vanilla.ink.findway.map.gen.MapGenerator
import ru.vanilla.ink.findway.utils.ResourceManager

/**
 * Created by hedgehog on 11.08.17.
 */

class AntTest : Screen{
    private val batch: SpriteBatch = SpriteBatch()
    private val cam: OrthographicCamera
    private val texture = Texture(Gdx.files.internal("tails.png"))

    init {
        batch.disableBlending()

        cam = OrthographicCamera(AppConstants.FIELD_SIZE.toFloat(), AppConstants.FIELD_SIZE.toFloat())
        //        char[][] bmap = (new MazeLoader("maze0.vll")).getMaze();
        val bmap = MapGenerator(AppConstants.FIELD_SIZE).getMaze()

        for (i in 0..AppConstants.FIELD_SIZE - 1) {
            for (j in 0..AppConstants.FIELD_SIZE - 1) {
                if (bmap[i][j] == 'F' || bmap[i][j] == '0')
                    Data.map[i][j] = Empty(ResourceManager.manager.get("floor_mazle.png", Texture::class.java))
                if (bmap[i][j] == 'W' || bmap[i][j] == '1')
                    Data.map[i][j] = Wall(ResourceManager.manager.get("wall_mazle.png", Texture::class.java))
                if (bmap[i][j] == '2') {
                    Data.map[i][j] = Exit(texture)
                    Data.exitList.add(Vector2(i.toFloat(), j.toFloat()))
                }
            }
        }
    }

    override fun hide() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun show() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(delta: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resize(width: Int, height: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dispose() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}