/**
 * Created by andrej on 18.9.2017
 */


package Itertools

class CombIter<T>(val alphabet: List<T>, val length: Int) : Iterator<List<T>> {
    private val maxVal = alphabet.size - 1
    private var indices = MutableList(length, { 0 })
    private var done = false

    override fun hasNext() = !done

    override fun next(): List<T> {
        val ret = indices.map { alphabet[it] }

        var i = indices.size - 1
        indices[i]++
        while (i > 0 && indices[i] > maxVal) {
            indices[i] = 0
            indices[--i]++
        }
        if (indices[0] > maxVal) done = true
        return ret
    }
}


fun <T> combinationsWithReplacement(alphabet: List<T>, length: Int) = CombIter<T>(alphabet, length)