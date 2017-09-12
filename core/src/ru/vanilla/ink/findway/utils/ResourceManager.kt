package ru.vanilla.ink.findway.utils

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture

/**
 * Created by hedgehog on 05.08.17.
 */
object ResourceManager{

    val manager = AssetManager()

    fun loadResources(){
        manager.apply {
            load("floor_mazle.png", Texture::class.java)
            load("wall_mazle.png", Texture::class.java)
            finishLoading()
        }
    }
}