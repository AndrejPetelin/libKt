package PowOfTwo

fun nearestLowerPowOfTwo(x: Long): Long {
    var ret = 0L;
    var curr = 1L;

    while (curr <= x) {
        ret = curr;
        curr = curr shl 1;
    }
    return ret;
}