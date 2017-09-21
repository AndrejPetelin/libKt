/**
 * Inspired by Python's itertools, combinatoric generators that generate combinations on the fly, while iterating them.
 * The objects store state internally so they can generate the next combination without use of yield which is currently
 * still experimental.
 *
 * Created by andrej on 18.9.2017
 */


package Itertools

/**
 * object that generates (in List<T>) combinations with repeats on the fly.
 */
class CombIterRepeat<T>(val alphabet: List<T>, val length: Int) : Iterator<List<T>> {
    private val maxVal = alphabet.size - 1

    /**
     * indices of each element within the returned List<T>. Used to generate current return of next() and to keep track
     * of state.
     */
    private var indices = MutableList(length, { 0 })

    // flipped to true in next() once we've reached the last element, returned in negated form by hasNext()
    private var done = false

    override fun hasNext() = !done

    override fun next(): List<T> {
        // this gets returned, the rest is just preparation for the next call
        val ret = indices.map { alphabet[it] }

        // start at the end
        var i = indices.size - 1
        indices[i]++

        // "carry" elements as long as they're at the end of alphabet
        // i.e. in decimal numbers, 123999 -> 124000
        while (i > 0 && indices[i] > maxVal) {
            indices[i] = 0      // reset to zero
            indices[--i]++      // increment element to the left
        }
        // we are done once we iterate through the alphabet in left-most position so we flip the bool
        if (indices[0] > maxVal) done = true
        return ret
    }
}


class CombIter<T>(val alphabet: List<T>, val length: Int): Iterator<List<T>> {
    private var indices = List(length, { 0 }).mapIndexed { index, i -> index }.toMutableList()
    private var done = false

    private fun  isMaxVal(i: Int, n: Int) = alphabet.size - (length - i) == n

    override fun hasNext() = !done

    override fun next(): List<T> {
        val ret = indices.map { alphabet[it] }
        var i = indices.size - 1

        while (i >= 0 && isMaxVal(i, indices[i])) --i

        if (i < 0) done = true
        else {
            var n = ++indices[i++]
            while (i < indices.size) {
                indices[i++] = ++n
            }
        }
        return ret
    }
}



fun <T> combinationsWithReplacement(alphabet: List<T>, length: Int) = CombIterRepeat<T>(alphabet, length)

fun combinationsWithReplacement(alphabet: String, length: Int) = combinationsWithReplacement(alphabet.map { it }, length)

fun <T> combinations(alphabet: List<T>, length: Int): CombIter<T> {
    if (length > alphabet.size) throw IndexOutOfBoundsException("combinations: length larger than alphabet size")
    return CombIter<T>(alphabet, length)
}

fun combinations(alphabet: String, length: Int) = combinations(alphabet.map { it }, length)