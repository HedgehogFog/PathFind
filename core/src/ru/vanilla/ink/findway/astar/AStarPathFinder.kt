package ru.vanilla.ink.findway.astar

import com.badlogic.gdx.utils.BinaryHeap
import com.badlogic.gdx.utils.IntArray
import ru.vanilla.ink.findway.info.Data
import ru.vanilla.ink.findway.map.element.Cell

/**
 * Created by hedgehog on 03.08.17.
 */
class AStarPathFinder(val width: Int, val height: Int){

    val open = BinaryHeap<PathNode>(width * 4, false)
    val nodes = arrayOfNulls<PathNode>(width * height)

    var runID = 0
    var unit: Cell? = null
    val path = IntArray()
    private var targetX = 0
    private var targetY = 0

    fun getPath(startX: Int, startY: Int, targetX: Int, targetY: Int, unit: Cell): IntArray {
        this.targetX = targetX
        this.targetY = targetY
        this.unit = unit

        path.clear()
        open.clear()

        runID++

        if (runID < 0) runID = 1

        var index = startY * width + startX

        var root = nodes[index]
        if (root == null){
            root = PathNode(0.0f)
            root.x = startX
            root.y = startY

            nodes[index] = root
        }

        root.parent = null
        root.pathCost = 0
        open.add(root, 0.0f)

        val lastColumn = width - 1
        val lastRow = height - 1

        var i = 0
        while (open.size > 0){
            var node = open.pop()
            if (node.x == targetX && node.y == targetY){
                while (node != root){
                    path.add(node.x)
                    path.add(node.y)
                    node = node.parent
                }
                break
            }
            node.closedID = runID

            val x = node.x
            val y = node.y

            if (x < lastColumn){
                addNode(node, x + 1, y, 10)
                if (y < lastRow)
                    addNode(node, x + 1, y + 1, 14)
                if (y > 0)
                    addNode(node, x + 1, y - 1, 14)
            }
            if (x > 0) {
                addNode(node, x - 1, y, 10)
                if (y < lastRow)
                    addNode(node, x - 1, y + 1, 14)
                if (y > 0)
                    addNode(node, x - 1, y - 1, 14)
            }

            if (y < lastRow)
                addNode(node, x, y + 1, 10)
            if (y > 0)
                addNode(node, x, y - 1, 10)
            i++
        }

         return path
    }

    private fun addNode(parent: PathNode, x: Int, y: Int, cost: Int){

        if (!isValid(x, y)) return

        val pathCost = parent.pathCost + cost
        val score = pathCost + Math.abs(x - targetX) + Math.abs(y - targetY)

        val index = y * width + x
        var node = nodes[index]

        if (node != null && node.runID == runID){
            if (node.closedID != runID && pathCost < node.pathCost) {
                open.setValue(node, score.toFloat())
                node.parent = parent
                node.pathCost = pathCost
            }
        } else {
            if (node == null){
                node = PathNode(0.0f)
                node.x = x
                node.y = y
                nodes[index] = node
            }
            open.add(node, score.toFloat())
            node.runID = runID
            node.parent = parent
            node.pathCost = pathCost
        }
    }

    private fun isValid(x: Int, y: Int): Boolean {
        if (Data.map[x][y].blocked(unit as Cell)){
            return false
        }
        return true
    }

}