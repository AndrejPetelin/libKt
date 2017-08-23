/**
 * Created by andrej on 23.8.2017
 */

package RandNums

import java.util.*

/**
 * generate a list of integers of size size (in the range [INT_MIN - INT_MAX])
 */
fun randIntList(size: Int): List<Int> {
    val randomgen = Random()
    return (0 until size).map { randomgen.nextInt() }
}


/**
 *
 */
