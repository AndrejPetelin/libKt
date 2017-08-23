/**
 * Created by andrej on 23.8.2017
 */

package SumInList


/**
 * is sum of two numbers from list equal to x?
 */
fun isSumInList(x: Int, list: List<Int>): Boolean {
    // sort the list - O(n*log(n))
    val xs = list.sorted()
    // pointers - left and right are indices of the number we're looking at on the left and the right side of the list
    var left = 0
    var right = xs.size - 1
    // sum is the sum of left and right - zero initialized but gets reassigned before the first check
    var sum = 0

    do {
        sum = xs[left] + xs[right]

        if (sum < x) ++left // increase the sum or unchanged
        else if (sum > x) --right   // decrease the sum or unchanged

    } while (sum != x && left < right)

    // we got here either by sum == x or left == right
    return x == sum
}

/**
 * which two numbers in list sum to x?
 */
fun <C: Collection<Int>> whichSumInList(x: Int, coll: C): Pair<Int, Int>? {
    // sort the list - O(n*log(n))
    val xs = coll.sorted()
    // pointers - left and right are indices of the number we're looking at on the left and the right side of the list
    var left = 0
    var right = xs.size - 1
    // sum is the sum of left and right - zero initialized but gets reassigned before the first check
    var sum = 0

    do {
        sum = xs[left] + xs[right]

        if (sum < x) ++left // increase the sum or unchanged
        else if (sum > x) --right   // decrease the sum or unchanged

    } while (sum != x && left < right)

    // we got here either by sum == x or left == right
    return if (x == sum) Pair(xs[left], xs[right]) else null
}


/**
 * at which index are the two numbers that sum to x?
 */
fun <C: Collection<Int>> whereSumInList(x: Int, coll: C) : Pair<Int, Int>? {
    val xs = coll.toTypedArray()
    val p = whichSumInList(x, coll)
    if (p == null) return null

    val ret1 = coll.indexOf(p.first)
    val ret2 = coll.indexOf(p.second)
    return Pair(ret1, ret2)
}

