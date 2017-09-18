package Tests

/**
 * Created by andrej on 18.9.2017
 */

import Itertools.combinationsWithReplacement

fun main(args: Array<String>) {
    val xs = combinationsWithReplacement(listOf(1,2,3,4), 4)

    for (x in xs) println(x)

    val ys = combinationsWithReplacement("abcd", 4)

    for (y in ys) println(y)
}