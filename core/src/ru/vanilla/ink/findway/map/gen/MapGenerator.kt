package ru.vanilla.ink.findway.map.gen

import java.util.*

/**
 * Created by hedgehog on 05.07.17.
 */

class MapGenerator(val size : Int) {

    val fullfill = 30
    val wallshort = 100

    val rand = Random()
    private val m = Array(size + 1) {CharArray(size + 1)}
    private val r = Array(2) { IntArray(size/2 * size/2)}

    var h = 0

    var startx = 0
    var starty = 0

    fun getMaze(): Array<CharArray> {

//        m = Array(size + 1) {CharArray(size + 1)}
//        r = Array(2) { IntArray(size/2 * size/2)}

        var i = 0
        while (i < size){
            var j = 0
            while (j < size){
                m[i][j] = '0'
                j++
            }
            i++
        }


        for (i in 0..size) {  //make border

            m[0][i] = '1'
            m[size - 1][i] = '1'

            m[i][0] = '1'
            m[i][size - 1] = '1'
        }

        initrandom()

        while (getrandom()) {

            if (m[starty][startx] == '1')
                continue
            if (rand.nextInt(100) > fullfill)
                continue
            var sx = 0
            var sy = 0

            do {
                sx = rand.nextInt(3) - 1
                sy = rand.nextInt(3) - 1
            } while (sx == 0 && sy == 0 || sx != 0 && sy != 0)

            while (m[starty][startx] == '0') {
                if (rand.nextInt(100) > wallshort) {
                    m[starty][startx] = '1'
                    break
                }

                m[starty][startx] = '1'
                startx += sx
                starty += sy

                m[starty][startx] = '1'
                startx += sx
                starty += sy
            }


        }

        m[rand.nextInt(30)][rand.nextInt(30)] = '2'
        return m
    }

    private fun initrandom() {
        var j = 0

        var y = 2
        for (y in 2..size step 2)
            for (x in 2..size step 2) {
                r[0][j] = x
                r[1][j] = y
                j++
            }
        h = j - 1
    }

    private fun getrandom(): Boolean {

        val i = (Random()).nextInt(h)

        startx = r[0][i]
        starty = r[1][i]

        r[0][i] = r[0][h]
        r[1][i] = r[1][h]

        return (--h) != 0

    }

}
