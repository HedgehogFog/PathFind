package ru.vanilla.ink.findway.astar

import com.badlogic.gdx.utils.BinaryHeap

/**
 * Created by hedgehog on 03.08.17.
 */
class PathNode(value: Float) : BinaryHeap.Node(value) {
    var runID = 0
    var closedID = 0
    var x = 0
    var y = 0
    var pathCost = 0
    var parent: PathNode? = null
}