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
class ProdIter<T>(val alphabet: List<T>, val length: Int) : Iterator<List<T>> {
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


/**
 * object that generates (in List<T>) combinations without repeats on the fly.
 */
class CombIter<T>(val alphabet: List<T>, val length: Int): Iterator<List<T>> {
    // ascending indices 0 until length
    private var indices = List(length, { 0 }).mapIndexed { index, i -> index }.toMutableList()
    private var done = false

    // checks if n is max value at position i
    private fun  isMaxVal(i: Int, n: Int) = alphabet.size - (length - i) == n

    override fun hasNext() = !done

    override fun next(): List<T> {
        // this gets returned, the rest is setup for the next call
        val ret = indices.map { alphabet[it] }
        var i = indices.size - 1

        // find first position from the right that can be incremented
        while (i >= 0 && isMaxVal(i, indices[i])) --i

        // if we've fallen through indices we're done, otherwise increment element at indices and reset indices to the
        if (i < 0) done = true
        else {
            // right of it to value + difference in index (ex. for length == 4, alphabet of "abcdefg" i == 1: adef
            var n = ++indices[i++]
            while (i < indices.size) {
                indices[i++] = ++n
            }
        }
        return ret
    }
}


/**
 * combinationsWithReplacement generates a list of all elements with replacements, so for alphabet of "abc" and length
 * of 3 you get ['a', 'a', 'a'], ['a', 'a', 'b'] ... ['b', 'c', 'c'], ['c', 'c', 'c']
 */
fun <T> product(alphabet: List<T>, length: Int) = ProdIter<T>(alphabet, length)

// overloaded for alphabet as String
fun product(alphabet: String, length: Int) = product(alphabet.map { it }, length)


/**
 * combinations generates a list of all element without replacement, so for alphabet "abcdef" and a length of 3 you get
 * ['a', 'b', 'c'], ['a', 'b', 'd'] ... ['c', 'd', 'f'], ['c', 'e', 'f'], ['d', 'e', 'f']
 */
fun <T> combinations(alphabet: List<T>, length: Int): CombIter<T> {
    if (length > alphabet.size) throw IndexOutOfBoundsException("combinations: length larger than alphabet size")
    return CombIter<T>(alphabet, length)
}

// overloaded for alphabet as String
fun combinations(alphabet: String, length: Int) = combinations(alphabet.map { it }, length)