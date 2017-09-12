package com.vanilla.finderway.controller

import com.vanilla.finderway.map.element.land.Dirt
import com.vanilla.finderway.map.element.land.Empty
import ru.vanilla.ink.findway.map.element.Cell
import ru.vanilla.ink.findway.map.element.unit.UnitWave
import java.util.Vector

/**
 * Created by hedgehog on 07.07.17.
 */
object WavePath {
    fun getPath(my_map: Array<Array<Cell>>, x: Int, y: Int, nx: Int,
                ny: Int): Vector<Action> {

        val map = Array(my_map.size + 2) { IntArray(my_map[0].size + 2) }

        for (j in 0..map[0].size - 1) {
            map[0][j] = 1001
            map[map.size - 1][j] = 1001
        }
        for (i in map.indices) {
            map[i][0] = 1001
            map[i][map[0].size - 1] = 1001
        }

        for (j in 1..my_map[0].size)
            for (i in 1..my_map.size) {
                var n = 1001

                if (my_map[i - 1][j - 1] != null) {
                    if (my_map[i - 1][j - 1] is Empty)
                        n = 1000
                    if (my_map[i - 1][j - 1] is Dirt)
                        n = 1000
                    if (my_map[i - 1][j - 1] is UnitWave)
                        n = 1000
                }
                map[i][j] = n
            }

        map[x + 1][y + 1] = 0

        var n = 1
        var isEnd = false
        while (!isEnd) {
            isEnd = true
            for (j in 1..map[0].size - 1 - 1)
                for (i in 1..map.size - 1 - 1)
                    if (map[i][j] == n - 1) {
                        for (k in 0..1) {
                            if (map[i + (1 + k * -2)][j] == 1000) {
                                map[i + (1 + k * -2)][j] = n
                                isEnd = false
                            }
                            if (map[i][j + (1 + k * -2)] == 1000) {
                                map[i][j + (1 + k * -2)] = n
                                isEnd = false
                            }
                        }
                    }
            n++
        }

        /*for (int j = 1; j < map[0].length - 1; j++) {
			for (int i = 1; i < map.length - 1; i++) {
				if (map[i][j] == 1001)
					System.out.print('x');
				else if (map[i][j] == 1000)
					System.out.print('*');
				else
					System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("Steps " + map[nx + 1][ny + 1]);
		System.out.println("new coords = " + nx + " " + ny + " old coords " + x+ " " + y);*/

        val path = Vector<Action>()
        var cx = nx + 1
        var cy = ny + 1
        var step = map[cx][cy]
        while (map[cx][cy] != 0) {
            var finded = false
            var k = 0
            while (k <= 1 && !finded) {
                if (map[cx + 1 - 2 * k][cy] == step - 1) {
                    finded = true
                    if (k == 0) {
                        path.add(Action.left)
                        cx++
                    }
                    if (k == 1) {
                        path.add(Action.right)
                        cx--
                    }
                    //System.out.println(path.get(path.size() - 1));
                } else {
                    if (map[cx][cy + 1 - 2 * k] == step - 1) {
                        finded = true
                        if (k == 0) {
                            path.add(Action.up)
                            cy++
                        }
                        if (k == 1) {
                            path.add(Action.down)
                            cy--
                        }
                        //System.out.println(path.get(path.size() - 1));
                    }
                }
                k++
            }
            step--
        }

        // for (Action a : path)
        // System.out.println(a);
        // System.out.println();
        return path

    }

}
