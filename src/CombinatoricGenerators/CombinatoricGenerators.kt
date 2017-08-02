/**
 * CombinatoricGenerators.kt - generators for creating lists of all possible combinations/permutations
 * as per Python's itertools.
 *
 * Created by andrej on 2.8.2017.
 */


package CombinatoricGenerators

fun permutationsWithReplacement(alphabet: String, length: Int): ArrayList<String> {
    val words = ArrayList<String>()
    fun build(word: String, n: Int) {
        if (n != length) {
            for (ch in alphabet) build(word + ch, n + 1)
        }
        else words.add(word)
    }

    build("", 0)
    return words
}


fun permutations(alphabet: String): ArrayList<String> {
    val words = ArrayList<String>()
    fun build(word: String, chars: String) {
        if (!chars.isEmpty()) {
            for (idx in chars.indices) {
                build(word + chars[idx], chars.filterIndexed { i, _ -> i != idx })
            }
        }
        else words.add(word)
    }

    build("", alphabet)
    return words
}


fun combinations(alphabet: String): ArrayList<String> {
    TODO("implement this")
}


fun combinationsWithReplacement(alphabet: String, n: Int): ArrayList<String> {
    TODO("implement this")
}


fun main(args: Array<String>) {
    val xs = permutationsWithReplacement("abc", 3)
    xs.map { println(it) }

    val ys = permutations("abcd")
    ys.map { println(it) }
}
