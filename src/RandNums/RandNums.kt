/**
 * Created by andrej on 23.8.2017
 *
 * TODO: Perhaps add an internal object so that the pseudo-random number generator doesn't get restarted with each call?
 */

package RandNums

import java.util.*


/**
 * generates a list of integers of size size (in the range [INT_MIN - INT_MAX])
 */
fun randIntList(size: Int): List<Int> {
    val randgen = Random()
    return (0 until size).map { randgen.nextInt() }
}


/**
 * generates a list of integers in range from min to max of size size
 */
fun randIntList(size: Int, min: Int, max: Int): List<Int> {
    val randgen = Random()
    return (0 until size).map { min + randgen.nextInt(max - min) }
}


/**
 * generates a list of long integers of size size (in the range [LONG_MIN - LONG_MAX])
 */
fun randLongList(size: Int): List<Long> {
    val randgen = Random()
    return (0 until size).map { randgen.nextLong() }
}


/**
 * generates a list of long integers in range from min to max of size size
 */
fun randLongList(size: Int, min: Long, max: Long): List<Long> {
    val randgen = Random()
    return (0 until size).map { min + randgen.nextLong() % (max - min) }
}


/**
 *  generates a list of floats in the range of 0.0f to 1.0f
 */
fun randFloatList(size: Int): List<Float> {
    val randgen = Random()
    return (0 until size).map { randgen.nextFloat() }
}


/**
 * generates a list of floats in the range of min to max
 */
fun randFloatList(size: Int, min: Float, max: Float): List<Float> {
    val randgen = Random()
    return (0 until size).map { min + randgen.nextFloat() * (max - min) }
}


/**
 *  generates a list of floats in the range of 0.0f to 1.0f
 */
fun randDoubleList(size: Int): List<Double> {
    val randgen = Random()
    return (0 until size).map { randgen.nextDouble() }
}


/**
 * generates a list of floats in the range of min to max
 */
fun randDoubleList(size: Int, min: Double, max: Double): List<Double> {
    val randgen = Random()
    return (0 until size).map { min + randgen.nextDouble() * (max - min) }
}