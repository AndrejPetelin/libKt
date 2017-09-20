/**
 * Created by andrej on 18.9.2017
 */


package Itertools

class CombIterRepeat<T>(val alphabet: List<T>, val length: Int) : Iterator<List<T>> {
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