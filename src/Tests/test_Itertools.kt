package Tests

/**
 * Created by andrej on 18.9.2017
 */

import Itertools.permutationsWithReplacement
import Itertools.combinations

fun main(args: Array<String>) {
    val xs = permutationsWithReplacement(listOf(1,2,3,4), 4)

    for (x in xs) println(x)

    val ys = permutationsWithReplacement("abcd", 4)

    for (y in ys) println(y)


    val zs = combinations(listOf(1,2,3,4,5,6), 3)

    for (z in zs) println(z)
}
