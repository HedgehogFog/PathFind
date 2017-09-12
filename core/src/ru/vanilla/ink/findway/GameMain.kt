package ru.vanilla.ink.findway

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import ru.vanilla.ink.findway.screen.GameScreen
import ru.vanilla.ink.findway.utils.ResourceManager

//object viewport : FitViewport(AppConstants.APP_WIDTH, AppConstants.APP_HEIGHT)

var currentScreen: Screen
    get() = (Gdx.app.applicationListener as Game).screen
    set(value) {
        (Gdx.app.applicationListener as Game).screen = value
    }


class GameMain : Game() {

    override fun create() {
        ResourceManager.loadResources()
        screen = GameScreen()
    }

}
