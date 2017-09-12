package ru.vanilla.ink.findway.info

import com.badlogic.gdx.math.Vector2
import ru.vanilla.ink.findway.utils.Arrays

/**
 * Created by hedgehog on 05.08.17.
 */
object Data {
    val map = Arrays.get2Dcell(AppConstants.FIELD_SIZE, AppConstants.FIELD_SIZE)

    val exitList = ArrayList<Vector2>()
}