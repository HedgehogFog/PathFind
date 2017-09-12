package ru.vanilla.ink.findway.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.vanilla.finderway.map.element.land.Empty
import com.vanilla.finderway.map.element.land.Exit
import com.vanilla.finderway.map.element.land.Wall
import com.vanilla.finderway.map.element.unit.Human
import ru.vanilla.ink.findway.info.AppConstants
import ru.vanilla.ink.findway.info.Data
import ru.vanilla.ink.findway.map.element.unit.UnitWave
import ru.vanilla.ink.findway.map.element.unit.astar.Helicopter
import ru.vanilla.ink.findway.map.gen.MapGenerator
import ru.vanilla.ink.findway.utils.Arrays
import ru.vanilla.ink.findway.utils.ResourceManager

/**
 * Created by hedgehog on 06.07.17.
 */
class GameScreen : Screen {

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


    private fun update(delta: Float) {
        val input = Gdx.input

        for (i in 0..AppConstants.FIELD_SIZE - 1)
            for (j in 0..AppConstants.FIELD_SIZE - 1)
                Data.map[i][j].update(Data.map, i, j, texture)


        //сдвиг камеры, масштабирование, вращение, ускорение
        if (input.isKeyPressed(Input.Keys.W)) cam.zoom -= delta
        if (input.isKeyPressed(Input.Keys.S)) cam.zoom += delta

        if (input.isKeyPressed(Input.Keys.Q)) cam.rotate(delta * 90)
        if (input.isKeyPressed(Input.Keys.E)) cam.rotate(-delta * 90)

        cam.update()

        if (input.justTouched()){
            val stepX = (Gdx.graphics.width / AppConstants.FIELD_SIZE).toFloat()
            val stepY = (Gdx.graphics.height / AppConstants.FIELD_SIZE).toFloat()
            val x = input.x.toFloat()
            val y = input.y.toFloat()
            for (i in 0..AppConstants.FIELD_SIZE - 1)
                for (j in 0..AppConstants.FIELD_SIZE - 1) {
                    if (x >= stepX * i && x <= stepX * (i + 1)
                            && y >= stepY * j && y <= stepY * (j + 1))
                        if (Data.map[i][AppConstants.FIELD_SIZE - j - 1] is Empty)
                            Data.map[i][AppConstants.FIELD_SIZE - j - 1] = Human(texture)
                }
        }
    }

    override fun show() {
    }

    override fun render(delta: Float) {


        update(delta)

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.projectionMatrix = cam.combined

        batch.begin()

        for (i in 0..AppConstants.FIELD_SIZE - 1) {
            for (j in 0..AppConstants.FIELD_SIZE - 1) {
                Data.map[i][j].draw(batch, i, j)
            }
        }

        batch.end()
    }

    override fun resize(width: Int, height: Int) {
//        viewport.update(width, height)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {
    }

    override fun dispose() {

    }
}
